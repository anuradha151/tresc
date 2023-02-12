package com.anuradha.tresc.service.impl;

import com.anuradha.tresc.dto.UsersRequestDto;
import com.anuradha.tresc.model.Users;
import com.anuradha.tresc.repository.UsersRepository;
import com.anuradha.tresc.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UsersRepository usersRepository;

    public Optional<Users> AddUser(UsersRequestDto user) {
        Users newUser = new Users();
        newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setRoles("ROLE_USER");
        return Optional.of(usersRepository.save(newUser));
    }
}
