package com.rajanish.splitwise.services;

import com.rajanish.splitwise.models.Users;
import com.rajanish.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public Users registerUser(String userName,String password,String mobileNumber){
        Users user= new Users();
        user.setUserName(userName);
        user.setPhoneNumber(mobileNumber);
        user.setHashedPassword(password);
        user=userRepository.save(user);
        return user;
    }
    public Users getUser(String userName){
        return userRepository.findUsersByUserName(userName);
    }
}
