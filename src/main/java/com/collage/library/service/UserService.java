package com.collage.library.service;

import com.collage.library.dto.UserDto;
import com.collage.library.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAllUsers();

    public User findUserById(Long id);

    public User saveUser(UserDto userDto);

    public User updateUser(Long id, User user);

    public User removeUser(Long id);
}
