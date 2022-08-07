package com.sergedanson.twitteruserrecommendationapi.controllers;


import com.sergedanson.twitteruserrecommendationapi.dto.RecommendedUserDto;
import com.sergedanson.twitteruserrecommendationapi.models.ContactTweets;
import com.sergedanson.twitteruserrecommendationapi.services.ContactTweetsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecommendUsers {


    final
    ContactTweetsService contactTweetsService;
    public RecommendUsers(ContactTweetsService contactTweetsService) {
        this.contactTweetsService = contactTweetsService;
    }

    @GetMapping("q2")
    @Operation(
            tags = {"q2"}
    )
    public String readQueryParameter(@RequestParam Long user_id, @RequestParam String type, @RequestParam String phrase, @RequestParam String hashtag) {
        try {
            List<ContactTweets> contactTweetsList  = contactTweetsService.findByUserIdAndTweetType(user_id, type);
            StringBuilder toBeRecommended = new StringBuilder();
            List<RecommendedUserDto> recommendedUsers = new ArrayList<>();

            contactTweetsList.forEach(i->{
                Double interactionScore = contactTweetsService.calculateInteractionScore(user_id, i.getContact().getUserId());
                Double keywordScore = contactTweetsService.calculateKeyWordScore(user_id, i.getContact().getUserId(), type, phrase, hashtag);
                Double hashtagScore = contactTweetsService.calculateHashtagScore(user_id,  i.getContact().getUserId());
                Double finalScore = interactionScore +  keywordScore +  hashtagScore;
                RecommendedUserDto recommendedUser = new RecommendedUserDto();
                recommendedUser.setUserId(i.getContact().getUserId());
                recommendedUser.setScreenName(i.getContact().getScreenName());
                recommendedUser.setDescription(i.getContact().getDescription());
                recommendedUser.setContactTweetText(i.getTweet().getText());
                recommendedUser.setHashtags(i.getTweet().getHashtags());
                recommendedUser.setInteractionScore(interactionScore);
                recommendedUser.setKeywordsScore(keywordScore);
                recommendedUser.setHashtagScore(hashtagScore);
                recommendedUser.setFinalScore(finalScore);
                recommendedUsers.add(recommendedUser);
            });

            //Filter out users with final score less than 1
            List<RecommendedUserDto> recommendableUsers =  recommendedUsers.stream().filter(i-> i.getFinalScore() >= 1).collect(Collectors.toList());

            recommendableUsers.forEach(i->{
                String contact = i.getUserId() +"\\" +i.getScreenName() +"\\" + i.getDescription() +"\\" +i.getContactTweetText()+"\n";
                toBeRecommended.append(contact);
            });
            return toBeRecommended.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


















