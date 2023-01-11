package com.collage.library.service;

import com.collage.library.config.CustomNotFoundException;
import com.collage.library.dto.UserDto;
import com.collage.library.model.Role;
import com.collage.library.model.User;
import com.collage.library.repository.LoanRepository;
import com.collage.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> findAllUsers() {
        return StreamSupport.stream(
                userRepository.findAll().spliterator(),
                false
        )
                .collect(Collectors.toList());
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new CustomNotFoundException("Not Found User!!..."));
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user=new User();
        user.setArea(userDto.getArea());
        user.setCi(userDto.getCi());
        user.setRole(Role.ADMIN);
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(obj->{
                    obj.setCi(user.getCi());
                    obj.setArea(user.getArea());
                    return userRepository.save(obj);
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found User!!..."));
    }

    @Override
    public User removeUser(Long id) {
        return userRepository.findById(id)
                .map(obj->{
                    loanRepository.findByUser_IdUser(obj.getIdUser())
                                    .ifPresent(loanRepository::delete);
                    userRepository.delete(obj);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found User!!..."));
    }
}
