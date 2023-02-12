package com.anuradha.tresc.service;

import com.anuradha.tresc.dto.UsersRequestDto;
import com.anuradha.tresc.model.Users;

import java.util.Optional;

public interface AuthService {
    public Optional<Users> AddUser(UsersRequestDto user);
}
