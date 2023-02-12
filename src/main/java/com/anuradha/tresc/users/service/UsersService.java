package com.anuradha.tresc.users.service;

import com.anuradha.tresc.users.Requests.UsersRequest;
import com.anuradha.tresc.users.model.Users;

import java.util.List;

public interface UsersService {
    public List<Users> GetAllUsers();

    public Users AddUser(UsersRequest user);
}
