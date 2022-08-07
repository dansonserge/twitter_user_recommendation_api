package com.sergedanson.twitteruserrecommendationapi.services.impl;

import com.sergedanson.twitteruserrecommendationapi.helpers.AppConstants;
import com.sergedanson.twitteruserrecommendationapi.helpers.AppUtils;
import com.sergedanson.twitteruserrecommendationapi.models.ContactTweets;
import com.sergedanson.twitteruserrecommendationapi.models.Tweet;
import com.sergedanson.twitteruserrecommendationapi.repositories.ContactTweetsRepository;
import com.sergedanson.twitteruserrecommendationapi.repositories.TweetsRepository;
import com.sergedanson.twitteruserrecommendationapi.services.ContactTweetsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContactTweetsServiceImpl implements ContactTweetsService {
    ContactTweetsRepository contactTweetsRepository;
    TweetsRepository tweetsRepository;

    final
    AppUtils appUtils;


    public ContactTweetsServiceImpl(ContactTweetsRepository contactTweetsRepository, TweetsRepository tweetsRepository, AppUtils appUtils) {
        this.contactTweetsRepository = contactTweetsRepository;
        this.tweetsRepository = tweetsRepository;
        this.appUtils = appUtils;
    }

    @Override
    public List<ContactTweets> findContactTweetsByUserId(Long userId) throws Exception {
        return contactTweetsRepository.findByUserId(userId);
    }

    @Override
    public List<ContactTweets> findByUserIdAndTweetType(Long userId, String type) throws Exception {
        if(AppConstants.BOTH.equalsIgnoreCase(type)){
            return contactTweetsRepository.findByUserId(userId);
        }else{
            return contactTweetsRepository.findByUserIdAndTweetType(userId, type);
        }
    }

    @Override
    public List<ContactTweets> findByUserIdAndContactUserId(Long userId, Long contactUserId) throws Exception {
        return  contactTweetsRepository.findByUserIdAndContactUserId(userId, contactUserId);
    }

    @Override
    public Double calculateInteractionScore(Long reqUserId, Long contactUserId){
        // Formula => log(1 + 2 * reply_count + retweet_count)
        List<ContactTweets> contactTweetsReply = contactTweetsRepository.findByUserIdAndContactUserIdAndTweetType(reqUserId, contactUserId, "reply");
        List<ContactTweets> contactTweetsRetweet = contactTweetsRepository.findByUserIdAndContactUserIdAndTweetType(reqUserId, contactUserId, "retweet");
        return Math.log(1+2*contactTweetsReply.size()+contactTweetsRetweet.size());
    }
    @Override
    public Double calculateKeyWordScore(Long reqUserId, Long contactUserId, String type, String phrase, String hashtag) {
        List<ContactTweets> contactTweets = new ArrayList<>();


        StringBuilder contactContentAndHashtags = new StringBuilder();


        if(AppConstants.BOTH.equalsIgnoreCase(type)){
            contactTweets =  contactTweetsRepository.findByUserIdAndContactUserId(reqUserId, contactUserId);
        }else{
            contactTweets =  contactTweetsRepository.findByUserIdAndContactUserIdAndTweetType(reqUserId, contactUserId, type);
        }
        contactTweets.forEach(i -> {
            contactContentAndHashtags.append(i.getTweet().getText()).append(i.getTweet().getHashtags());
        });


        int keywordsMatchesCountOnPhrase = StringUtils.countMatches(contactContentAndHashtags.toString(), phrase);
        int keywordsMatchesCountOnHashtag = StringUtils.countMatches(contactContentAndHashtags.toString(), hashtag);
        int totalNumberOfMatches = keywordsMatchesCountOnPhrase + keywordsMatchesCountOnHashtag;

        if( totalNumberOfMatches >0){
            return 1+Math.log(totalNumberOfMatches);
        }
        else{
            return 1.0;
        }
    }

    @Override
    public Double calculateHashtagScore(Long reqUserId, Long contactUserId) {
        List<Tweet> reqUserTweets = tweetsRepository.findTweetsByUserId(reqUserId);
        List<Tweet> contactUserTweets = tweetsRepository.findTweetsByUserId(contactUserId);

        StringBuilder reqUserHashtags = new StringBuilder();
        StringBuilder contactUserHashtags = new StringBuilder();

        reqUserTweets.forEach(i->{
            reqUserHashtags.append(i.getHashtags());
        });
        contactUserTweets.forEach(i->{
            contactUserHashtags.append(i.getHashtags());
        });

        List<String> hashtagsTobeIgnored = appUtils.getPopularHashTagsFromFile();

        List<String> reqUserHashtagsArray = Arrays.asList(reqUserHashtags.toString().split(","));
        List<String> contactUserHashtagsArray = Arrays.asList(contactUserHashtags.toString().split(","));

        reqUserHashtagsArray.removeAll(hashtagsTobeIgnored);
        contactUserHashtagsArray.removeAll(hashtagsTobeIgnored);

        List<String> recurringHashtags = new ArrayList<>();


        reqUserHashtagsArray.forEach(i->{
            contactUserHashtagsArray.forEach(j->{
                if(i.equalsIgnoreCase(j)){
                    recurringHashtags.add(j);
                }
            });
        });
        if(recurringHashtags.size()> 10){
           return 1+ Math.log(1+ recurringHashtags.size() -10);
        }else {
            return 1.0;
        }
    }
}
