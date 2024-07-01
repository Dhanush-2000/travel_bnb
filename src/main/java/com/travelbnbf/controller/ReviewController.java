package com.travelbnbf.controller;

import com.travelbnbf.entity.Consumer;
import com.travelbnbf.entity.Reviews;
import com.travelbnbf.repository.PropertyRepository;
import com.travelbnbf.repository.ReviewsRepository;
import com.travelbnbf.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;
    private ReviewsRepository reviewsRepository;
    private final PropertyRepository propertyRepository;

    public ReviewController(ReviewService reviewService, ReviewsRepository reviewsRepository,
                            PropertyRepository propertyRepository) {
        this.reviewService = reviewService;
        this.reviewsRepository = reviewsRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addReview")
    public ResponseEntity<?> addReview(
            @AuthenticationPrincipal Consumer consumer,
            @RequestParam Long propertyId,
            @RequestBody Reviews review
            ){
        if (reviewsRepository.findReviewsByUser(consumer,propertyRepository.findById(propertyId).get())!=null){
            return new ResponseEntity<>("review already given",HttpStatus.OK);
        }
        else {
            Reviews reviews = reviewService.addReview(consumer, propertyId, review);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
    }

    @GetMapping("/getReviewsByUser")
    public ResponseEntity<List<Reviews>> getReviewsByUser(@AuthenticationPrincipal Consumer consumer){
        List<Reviews> reviewsByUser = reviewService.getReviewsByUser(consumer);
        return new ResponseEntity<>(reviewsByUser,HttpStatus.OK);
    }
}
