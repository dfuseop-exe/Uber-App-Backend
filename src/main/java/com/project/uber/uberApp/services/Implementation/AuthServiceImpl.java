package com.project.uber.uberApp.services.Implementation;

import com.project.uber.uberApp.dto.DriverDTO;
import com.project.uber.uberApp.dto.SignUpDTO;
import com.project.uber.uberApp.dto.UserDTO;
import com.project.uber.uberApp.entities.User;
import com.project.uber.uberApp.entities.enums.Role;
import com.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.project.uber.uberApp.repositories.UserRepository;
import com.project.uber.uberApp.services.AuthService;
import com.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDTO signUp(SignUpDTO signUpDTO) {
        Boolean isAlreadyPresent = userRepository.existsUserByEmail(signUpDTO.getEmail());
        if (isAlreadyPresent) throw new RuntimeConflictException("Email already in use");

        User user = modelMapper.map(signUpDTO, User.class);
        user.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(user);

        //create user related entities
        riderService.createNewRider(user);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onboardNewDriver(Long userId) {
        return null;
    }


}
