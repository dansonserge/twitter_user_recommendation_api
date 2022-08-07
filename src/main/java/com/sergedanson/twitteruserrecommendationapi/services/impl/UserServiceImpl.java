package com.sergedanson.twitteruserrecommendationapi.services.impl;


import com.sergedanson.twitteruserrecommendationapi.models.User;
import com.sergedanson.twitteruserrecommendationapi.repositories.UsersRepository;
import com.sergedanson.twitteruserrecommendationapi.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<User> getUserById(Long userId) throws Exception {
        return usersRepository.findById(userId);
    }
}
