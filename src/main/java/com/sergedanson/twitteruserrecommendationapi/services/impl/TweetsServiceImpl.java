package com.sergedanson.twitteruserrecommendationapi.services.impl;

import com.sergedanson.twitteruserrecommendationapi.models.Tweet;
import com.sergedanson.twitteruserrecommendationapi.models.User;
import com.sergedanson.twitteruserrecommendationapi.repositories.TweetsRepository;
import com.sergedanson.twitteruserrecommendationapi.services.TweetsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetsServiceImpl implements TweetsService {


    TweetsRepository tweetsRepository;

    public TweetsServiceImpl(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }


    @Override
    public List<Tweet> getTweetsByUser(User user) {
        return tweetsRepository.findByUser(user);
    }

    @Override
    public List<Tweet> getTweetsByType(String typeName) {
        return tweetsRepository.findAllByType(typeName);
    }

    @Override
    public Optional<Tweet> findTweetById(Long tweetId) {
        return tweetsRepository.findById(tweetId);
    }
}
