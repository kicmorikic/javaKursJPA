package com.example.ipa.demo;

import com.example.ipa.repository.IUserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final IUserRepository userRepository;


    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void updateUser(Integer status, String name)
    {
        userRepository.updateUserSetStatusForNameNative(status, name);
    }
}
