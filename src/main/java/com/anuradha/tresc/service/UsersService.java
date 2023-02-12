package com.anuradha.tresc.service;

import com.anuradha.tresc.dto.UsersRequestDto;
import com.anuradha.tresc.model.Users;

import java.util.List;

public interface UsersService {
    public List<Users> GetAllUsers();

    public Users AddUser(UsersRequestDto user);
}
