package com.sergedanson.twitteruserrecommendationapi.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RecommendedUserDto {
    Long userId;
    String screenName;
    String description;
    String contactTweetText;
    String hashtags;
    Double interactionScore;
    Double hashtagScore;
    Double keywordsScore;
    Double finalScore;
}
