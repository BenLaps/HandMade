package com.handmade.handmade.service;



import com.handmade.handmade.models.User;
import com.handmade.handmade.models.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

    boolean currentUserHasRole(String role);

    boolean isAdmin();
}