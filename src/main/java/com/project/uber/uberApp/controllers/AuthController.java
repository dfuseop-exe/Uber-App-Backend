package com.project.uber.uberApp.controllers;

import com.project.uber.uberApp.dto.SignUpDTO;
import com.project.uber.uberApp.dto.UserDTO;
import com.project.uber.uberApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/test")
    public ResponseEntity<String> authCon(){
        return ResponseEntity.ok("Working") ;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        return ResponseEntity.ok(authService.signUp(signUpDTO));
    }
}
