package com.task.user.service;

import com.task.user.dto.ApiResponse;
import com.task.user.dto.UserDto;
import com.task.user.model.User;
import com.task.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo){
        this.repo=repo;
    }



    public ApiResponse<User> saveUser(UserDto userDto){
        Optional<User> existingUser = repo.findByEmail(userDto.getEmail());
        if(existingUser.isPresent()){
            throw new RuntimeException("User is already present");
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setZipCode(userDto.getZipCode());
        user.setState(userDto.getState());
        user.setCountry(userDto.getCountry());
        user.setPhone(userDto.getPhone());
        User savedUser = repo.save(user);
        return new ApiResponse<>("success",HttpStatus.CREATED.value(), "User updated successfully", savedUser);
    }


    public ApiResponse<List<User>> getAllUsers(){
        return new ApiResponse<>("success",HttpStatus.OK.value(), "Users fetched successfully", repo.findAll());
    }

    public ApiResponse<User> updateUser(UserDto userDto,int id){
        Optional<User> optionalUser = repo.findById(id);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User with this id "+id+" not exists");
        }
        User user = optionalUser.get();
        if (userDto.getPhone() != null) user.setPhone(userDto.getPhone());
        if (userDto.getName() != null) user.setName(userDto.getName());
        if (userDto.getCity() != null) user.setCity(userDto.getCity());
        if (userDto.getEmail() != null) user.setEmail(userDto.getEmail());
        if (userDto.getState() != null) user.setState(userDto.getState());
        if (userDto.getAddress() != null) user.setAddress(userDto.getAddress());
        if (userDto.getCountry() != null) user.setCountry(userDto.getCountry());
        if (userDto.getZipCode() != null) user.setZipCode(userDto.getZipCode());

        User updatedUser = repo.save(user);
        return new ApiResponse<>("success",HttpStatus.CREATED.value(), "User updated successfully", updatedUser);

    }

    public ApiResponse<User> deleteUser(int id){
        System.out.println(id);
        Optional<User> optionalUser = repo.findById(id);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User with this id "+id+" not exists");
        }
        User user = optionalUser.get();
        repo.delete(user);
        return new ApiResponse<>("success",HttpStatus.OK.value(), "User deleted successfully", null);
    }

}

