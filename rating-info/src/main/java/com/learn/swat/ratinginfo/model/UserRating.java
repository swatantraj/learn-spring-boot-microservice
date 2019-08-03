package com.learn.swat.ratinginfo.model;

import java.util.List;

public class UserRating {
    public UserRating() {
    }

    public UserRating(List<Rating> catalogItemList) {
        this.userRatings = userRatings;
    }

    List<Rating> userRatings;

    public List<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<Rating> userRatings) {
        this.userRatings = userRatings;
    }
}
