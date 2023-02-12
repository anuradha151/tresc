package com.anuradha.tresc.auth.controller;

import com.anuradha.tresc.UserSecurity.dao.JpaUserDetailsService;
import com.anuradha.tresc.UserSecurity.model.UserSecurity;
import com.anuradha.tresc.auth.request.AuthenticationRequest;
import com.anuradha.tresc.auth.service.AuthService;
import com.anuradha.tresc.config.JwtUtils;
import com.anuradha.tresc.dto.AuthTokenDto;
import com.anuradha.tresc.users.Requests.UsersRequest;
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
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JpaUserDetailsService jpaUserDetailsService;

    private final AuthService authService;

    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthTokenDto> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword(), new ArrayList<>()));
        final UserDetails user = jpaUserDetailsService.loadUserByUsername(request.getEmail());
        if (user == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        String jwt = jwtUtils.generateToken(user);
        return ResponseEntity.ok(AuthTokenDto.builder().accessToken(jwt).build());
    }

    @PostMapping("/register")
    public ResponseEntity<UserSecurity> register(@RequestBody UsersRequest user) throws Exception {
        return ResponseEntity.ok(authService.AddUser(user).map(UserSecurity::new).orElseThrow(() -> new Exception("Unknown")));
    }

}
