package com.task.user.controller;

import com.task.user.dto.ApiResponse;
import com.task.user.dto.UserDto;
import com.task.user.model.User;
import com.task.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(" ")
    public ApiResponse<User> addUser(@RequestBody UserDto userDto){
        return service.saveUser(userDto);
    }

    @GetMapping(" ")
    public ApiResponse<List<User>> getUser(){
        return service.getAllUsers();
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@RequestBody UserDto userDto,@PathVariable int id){
        return service.updateUser(userDto,id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<User> deleteUser(@PathVariable int id){
        System.out.println(id);
        return service.deleteUser(id);
    }

}
