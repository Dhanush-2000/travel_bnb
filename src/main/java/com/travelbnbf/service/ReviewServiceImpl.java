package com.travelbnbf.service;

import com.travelbnbf.entity.Consumer;
import com.travelbnbf.entity.Property;
import com.travelbnbf.entity.Reviews;
import com.travelbnbf.repository.PropertyRepository;
import com.travelbnbf.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewsRepository reviewsRepository;
    private PropertyRepository propertyRepository;

    public ReviewServiceImpl(ReviewsRepository reviewsRepository, PropertyRepository propertyRepository) {
        this.reviewsRepository = reviewsRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Reviews addReview(Consumer consumer, Long propertyId, Reviews review) {

        Optional<Property> byId = propertyRepository.findById(propertyId);
        if (byId.isPresent()){
            Property property = byId.get();
            review.setProperty(property);
            review.setConsumer(consumer);
        }
        Reviews savedData = reviewsRepository.save(review);
        return savedData;
    }

    @Override
    public List<Reviews> getReviewsByUser(Consumer consumer) {
        List<Reviews> reviewsByUser = reviewsRepository.findByConsumerReview(consumer);
        return reviewsByUser;
    }


}
