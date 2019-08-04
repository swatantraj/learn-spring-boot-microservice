package com.learn.swat.ratinginfo.resources;

import com.learn.swat.ratinginfo.model.Rating;
import com.learn.swat.ratinginfo.model.UserRating;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(new Rating("1",4), new Rating("2",3));
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRatings(ratings);
        return userRating;
    }
}
