package edu.iu.c322.trackingservice.repository;

import edu.iu.c322.trackingservice.model.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<Tracking, Integer> {

    public Tracking findByOrderIdAndItemId(int orderId, int itemId);
}
