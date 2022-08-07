package com.sergedanson.twitteruserrecommendationapi.repositories;
import com.sergedanson.twitteruserrecommendationapi.models.ContactTweets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ContactTweetsRepository extends JpaRepository<ContactTweets, Long> {


    @Query("select c from ContactTweets c where c.userId = ?1 and c.tweet.type = ?2")
    List<ContactTweets> findByUserIdAndTweetType(Long userId, String type);

    @Query("select c from ContactTweets c where c.userId=?1 and c.contact.userId=?2")
    List<ContactTweets> findByUserIdAndContactUserId(Long userId, Long contactUserId);

    //@Query("select c from ContactTweets c where c.userId=?1 and c.contact.userId=?2 and c.tweet.type = ?3")
    @Query(value="select * from contact_tweets c inner join tweets t ON c.tweet_id = t.tweet_id inner join users u ON u.user_id = c.user_id where c.user_id=?1 and c.contact_user = ?2 and t.type= ?3", nativeQuery = true)
    List<ContactTweets> findByUserIdAndContactUserIdAndTweetType(Long userId, Long contactUserId, String type);

    @Query("select c from ContactTweets c where c.userId = ?1")
    List<ContactTweets> findByUserId(Long userId);



}
