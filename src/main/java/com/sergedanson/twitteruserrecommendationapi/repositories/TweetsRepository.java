package com.sergedanson.twitteruserrecommendationapi.repositories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sergedanson.twitteruserrecommendationapi.models.ContactTweets;
import com.sergedanson.twitteruserrecommendationapi.models.Tweet;
import com.sergedanson.twitteruserrecommendationapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Repository
public interface TweetsRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByUser(User user);

    List<Tweet> findAllByType(String typeName);

    @Query(value="select * from tweets t where t.user_id = ?1", nativeQuery = true)
    List<Tweet> findTweetsByUserId(Long userId);


}
