package com.sergedanson.twitteruserrecommendationapi.repositories;


import com.sergedanson.twitteruserrecommendationapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

}
