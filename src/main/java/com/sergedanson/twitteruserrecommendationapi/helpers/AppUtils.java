package com.sergedanson.twitteruserrecommendationapi.helpers;


import com.sergedanson.twitteruserrecommendationapi.models.ContactTweets;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class AppUtils {
    public List<String> getPopularHashTagsFromFile(){
        String file = "src/main/resources/popular_hashtags.txt";

        Scanner sc = null;
        try {
            List<String> listOfPopularHashtags = new ArrayList<String>();
            sc = new Scanner(new File(file));
            sc.useDelimiter(" ");
            String str;
            while (sc.hasNext()) {
                str = sc.next();
                listOfPopularHashtags .add(str);
            }
            return  listOfPopularHashtags;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
