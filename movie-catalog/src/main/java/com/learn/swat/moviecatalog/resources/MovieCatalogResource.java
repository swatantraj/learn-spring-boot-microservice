package com.learn.swat.moviecatalog.resources;

import com.learn.swat.moviecatalog.model.CatalogItem;
import com.learn.swat.moviecatalog.model.Movie;
import com.learn.swat.moviecatalog.model.Rating;
import com.learn.swat.moviecatalog.model.UserRating;
import com.learn.swat.moviecatalog.services.MovieInfoService;
import com.learn.swat.moviecatalog.services.UserDetailService;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Resource
    @Qualifier("second")
    RestTemplate restTemplate;

    @Resource
    WebClient.Builder webClientBuilder;

    @Resource
    MovieInfoService movieInfoService;

    @Resource
    UserDetailService userDetailService;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = userDetailService.getUserRating(userId);
        return userRating.getUserRatings().stream().map(rating -> {
            return movieInfoService.getCatalogItem(rating);
        }).collect(Collectors.toList());

    }

}

// WebClientBuilder does not work with @loadBalanced Annotation

//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://movie-service/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();
