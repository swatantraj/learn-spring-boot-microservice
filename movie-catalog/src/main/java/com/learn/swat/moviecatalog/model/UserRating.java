package com.learn.swat.moviecatalog.model;

import java.util.List;

public class UserRating {
    String userId;
    List<Rating> userRatings;

    public UserRating() {
    }

    public UserRating(String userId, List<Rating> userRatings) {
        this.userRatings = userRatings;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<Rating> userRatings) {
        this.userRatings = userRatings;
    }
}
