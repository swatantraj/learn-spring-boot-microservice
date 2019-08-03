package com.learn.swat.moviecatalog.resources;

import com.learn.swat.moviecatalog.model.CatalogItem;
import com.learn.swat.moviecatalog.model.Movie;
import com.learn.swat.moviecatalog.model.Rating;
import com.learn.swat.moviecatalog.model.UserRating;
import com.netflix.discovery.DiscoveryClient;
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
    private DiscoveryClient discoveryClient;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = restTemplate.getForObject("http://rating-service/ratingsdata/users/foo", UserRating.class);

        System.out.println(userRating.getUserRatings());
        return userRating.getUserRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-service/movies/" + rating.getMovieId(), Movie.class);
// WebClientBuilder does not work with @loadBalanced Annotation

//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://movie-service/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();
            return new CatalogItem(movie.getName(), "Test", rating.getRating());

        }).collect(Collectors.toList());

    }


}
