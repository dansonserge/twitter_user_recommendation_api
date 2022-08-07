package com.sergedanson.twitteruserrecommendationapi.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="contact_tweets")
public class ContactTweets {

    @Id
    @Column(name = "contact_tweets_id")
    private Long contactTweetsId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_user")
    private User contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;






}
