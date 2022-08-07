package com.sergedanson.twitteruserrecommendationapi.services;

import com.sergedanson.twitteruserrecommendationapi.models.ContactTweets;

import java.util.List;

public interface ContactTweetsService {
    List<ContactTweets> findContactTweetsByUserId(Long userId) throws Exception;
    List<ContactTweets> findByUserIdAndTweetType(Long userId, String type) throws Exception;

    List<ContactTweets> findByUserIdAndContactUserId(Long userId, Long contactUserId) throws Exception;
    Double calculateInteractionScore(Long reqUserId, Long contactUserId);
    public Double calculateKeyWordScore(Long reqUserId, Long contactUserId, String type, String phrase, String hashtag) ;

    public Double calculateHashtagScore (Long reqUserId, Long contactUserId);


}
