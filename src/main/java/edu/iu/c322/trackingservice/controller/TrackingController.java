package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.model.Tracking;
import edu.iu.c322.trackingservice.model.UpdateRequest;
import edu.iu.c322.trackingservice.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trackings")
public class TrackingController {

    private OrderRepository repository;

    public TrackingController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{orderId}/{itemId}")
    public Tracking getByOrderIdAndItemId(@PathVariable int orderId, @PathVariable int itemId) {
        return repository.getTrackingByOrderAndItemId(orderId, itemId);
    }

    @PutMapping("/{orderId}")
    public void updateTrackingStatus(@RequestBody UpdateRequest updateRequest, @PathVariable int orderId) {
        repository.updateTrackingStatus(updateRequest, orderId);
    }
}
