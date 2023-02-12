package com.anuradha.tresc.auth.service;

import com.anuradha.tresc.users.Requests.UsersRequest;
import com.anuradha.tresc.users.model.Users;

import java.util.Optional;

public interface AuthService {
    public Optional<Users> AddUser(UsersRequest user);
}
