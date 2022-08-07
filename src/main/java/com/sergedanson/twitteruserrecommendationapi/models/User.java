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
@Table(name="users")
public class User {

    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "screen_name")
    private String screenName;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL, mappedBy = "tweetId")
    @JsonIgnore
    List<Tweet> tweetsList;

    @OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL, mappedBy = "tweet")
    @JsonIgnore
    List<ContactTweets> contactTweets;

}
