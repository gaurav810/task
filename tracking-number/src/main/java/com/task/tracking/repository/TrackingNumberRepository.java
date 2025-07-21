package com.task.tracking.repository;

import com.task.tracking.entity.TrackingNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingNumberRepository extends JpaRepository<TrackingNumber, Integer> {
}
