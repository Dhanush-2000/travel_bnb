package com.travelbnbf.repository;

import com.travelbnbf.entity.Consumer;
import com.travelbnbf.entity.Property;
import com.travelbnbf.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    @Query("select r from Reviews r where r.consumer=:user and r.property=:property")
    Reviews findReviewsByUser(@Param("user") Consumer user, Property property);

    @Query("select r from Reviews r where r.consumer=:consumer")
   List<Reviews> findByConsumerReview(@Param("consumer") Consumer consumer);
}