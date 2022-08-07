package com.sergedanson.twitteruserrecommendationapi.services;

import com.sergedanson.twitteruserrecommendationapi.models.Tweet;
import com.sergedanson.twitteruserrecommendationapi.models.User;

import java.util.List;
import java.util.Optional;

public interface TweetsService {

    List<Tweet> getTweetsByUser(User user) throws Exception;;
    List<Tweet> getTweetsByType(String typeName) throws Exception;;

    Optional<Tweet> findTweetById(Long tweetId) throws Exception;;
}
