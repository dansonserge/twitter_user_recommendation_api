package com.sergedanson.twitteruserrecommendationapi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tweets")
public class Tweet {

    @Id
    @Column(name = "tweet_id")
    private long tweetId;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    private String type;

    @Column(name = "hashtags")
    private String hashtags;

    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL, mappedBy = "tweet")
    @JsonIgnore
    List<ContactTweets> contactTweets;
}
