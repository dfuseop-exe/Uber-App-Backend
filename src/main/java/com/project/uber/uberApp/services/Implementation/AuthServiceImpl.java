package com.project.uber.uberApp.services.Implementation;

import com.project.uber.uberApp.dto.DriverDTO;
import com.project.uber.uberApp.dto.SignUpDTO;
import com.project.uber.uberApp.dto.UserDTO;
import com.project.uber.uberApp.entities.Driver;
import com.project.uber.uberApp.entities.User;
import com.project.uber.uberApp.entities.enums.Role;
import com.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.project.uber.uberApp.repositories.DriverRepository;
import com.project.uber.uberApp.repositories.UserRepository;
import com.project.uber.uberApp.security.JWTService;
import com.project.uber.uberApp.services.AuthService;
import com.project.uber.uberApp.services.DriverService;
import com.project.uber.uberApp.services.RiderService;
import com.project.uber.uberApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService ;
    private final DriverRepository driverRepository ;
    private final DriverService driverService ;

    @Override
    public String[] login(String email, String password) {
        String[] tokens = new String[2];
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        tokens[0] = accessToken;
        tokens[1] = refreshToken;

        return tokens ;
    }

    @Override
    @Transactional
    public UserDTO signUp(SignUpDTO signUpDTO) {
        Boolean isAlreadyPresent = userRepository.existsUserByEmail(signUpDTO.getEmail());
        if (isAlreadyPresent) throw new RuntimeConflictException("Email already in use");

        User user = modelMapper.map(signUpDTO, User.class);

        user.setRoles(Set.of(Role.RIDER));

        //encode pass
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        //create user related entities
        riderService.createNewRider(user);

        //wallet related services
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    public DriverDTO onboardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id "+userId));

        if(user.getRoles().contains(Role.DRIVER))
            throw new RuntimeConflictException("User with id "+userId+" is already a Driver");

        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();

        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);

        Driver savedDriver = driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver, DriverDTO.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found " +
                "with id: "+userId));

        return jwtService.generateAccessToken(user);
    }
}

