package com.rajanish.splitwise.controllers;

import com.rajanish.splitwise.dtos.RegisterUserRequestDto;
import com.rajanish.splitwise.dtos.UserLoginRequestDto;
import com.rajanish.splitwise.models.Users;
import com.rajanish.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/users/register")
    public Users registerUser(@RequestBody RegisterUserRequestDto request){
        return userService.registerUser(request.getUserName(),
                request.getHashedPassword(),
                request.getPhoneNumber());

    }
    @PostMapping ("/user/login")
    public boolean canLogin(@RequestBody UserLoginRequestDto request){
       Users user=userService.getUser(request.getUserName());
        if(user.getUserName().length()>0)
            if(request.getHashedPassword().equals(user.getHashedPassword()))
                return true;
        return false;
    }
}
