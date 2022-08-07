package com.sergedanson.twitteruserrecommendationapi.services;

import com.sergedanson.twitteruserrecommendationapi.models.ContactTweets;
import com.sergedanson.twitteruserrecommendationapi.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long userId) throws Exception;

}
