package com.travelbnbf.service;

import com.travelbnbf.entity.Consumer;
import com.travelbnbf.entity.Reviews;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReviewService {
    Reviews addReview(Consumer consumer, Long propertyId, Reviews review);

    List<Reviews> getReviewsByUser(Consumer consumer);

}
