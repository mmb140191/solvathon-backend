package com.lti.solvathon.service;

import com.lti.solvathon.model.UserInformation;
import com.lti.solvathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInformation saveData(UserInformation userInformation){
        return userRepository.save(userInformation);
    }
}
