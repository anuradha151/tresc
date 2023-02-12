package com.anuradha.tresc.controller;

import com.anuradha.tresc.service.impl.JpaUserDetailsServiceImpl;
import com.anuradha.tresc.config.security.UserSecurity;
import com.anuradha.tresc.dto.AuthRequestDto;
import com.anuradha.tresc.service.AuthService;
import com.anuradha.tresc.config.security.JwtUtils;
import com.anuradha.tresc.dto.AuthTokenDto;
import com.anuradha.tresc.dto.UsersRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JpaUserDetailsServiceImpl jpaUserDetailsServiceImpl;

    private final AuthService authService;

    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate/v1")
    public ResponseEntity<AuthTokenDto> authenticate(@RequestBody AuthRequestDto request, HttpServletResponse response) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword(), new ArrayList<>()));
        final UserDetails user = jpaUserDetailsServiceImpl.loadUserByUsername(request.getEmail());
        if (user == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        String jwt = jwtUtils.generateToken(user);
        return ResponseEntity.ok(AuthTokenDto.builder().accessToken(jwt).build());
    }

    @PostMapping("/register/v1")
    public ResponseEntity<UserSecurity> register(@RequestBody UsersRequestDto user) throws Exception {
        return ResponseEntity.ok(authService.AddUser(user).map(UserSecurity::new).orElseThrow(() -> new Exception("Unknown")));
    }

}
