package com.collage.library.controller;

import com.collage.library.dto.UserDto;
import com.collage.library.model.User;
import com.collage.library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(
                service.findAllUsers()
                        .stream()
                        .map(UserDto::from)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(
                UserDto.from(
                        service.findUserById(id)
                )
        );
    }

    @PostMapping("/save")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(
                UserDto.from(
                        service.saveUser(userDto)
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(
                UserDto.from(
                        service.updateUser(id,User.from(userDto))
                )
        );
    }

    @DeleteMapping("/dell")
    public ResponseEntity<UserDto> dellUser(@RequestParam("id") Long id){
        return ResponseEntity.ok(
                UserDto.from(
                        service.removeUser(id)
                )
        );
    }
}
