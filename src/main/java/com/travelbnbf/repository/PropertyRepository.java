package com.travelbnbf.repository;

import com.travelbnbf.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("Select p From Property p JOIN Location l ON p.location=l.id join Country c on p.country=c.id where l.name=:locationName or c.name=:locationName")
    List<Property> searchPropertyByLocation(@Param("locationName") String locationName);
}