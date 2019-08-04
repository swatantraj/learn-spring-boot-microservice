package com.learn.swat.moviecatalog.services;

import com.learn.swat.moviecatalog.model.Rating;
import com.learn.swat.moviecatalog.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;

@Service
public class UserDetailService {

    @Resource
    @Qualifier("second")
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating"/*,
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            }*/)
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        UserRating userRating = restTemplate.getForObject("http://rating-service/ratingsdata/users/foo", UserRating.class);
        return userRating;
    }

    private UserRating getFallbackUserRating(String userId, Throwable t) {
        UserRating userRating = new UserRating();
        System.out.println("fallback for RATING");
        System.out.println(t.getMessage());
        userRating.setUserId(userId);
        userRating.setUserRatings(Arrays.asList(new Rating("TestRating", 0)));
        return userRating;
    }

}
