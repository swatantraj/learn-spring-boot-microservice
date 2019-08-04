package com.learn.swat.moviecatalog.services;

import com.learn.swat.moviecatalog.model.CatalogItem;
import com.learn.swat.moviecatalog.model.Movie;
import com.learn.swat.moviecatalog.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieInfoService {

    @Resource
    @Qualifier("second")
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), "Description for movie - "+movie.getName(), rating.getRating());
    }

    private CatalogItem getFallbackCatalogItem(Rating rating, Throwable t) {
        System.out.println("fallback for CATALOG");
        System.out.println(t.getMessage());
        return new CatalogItem("No Movie Available", "No Desc", rating.getRating());
    }


}
