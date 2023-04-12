package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.model.Item;
import edu.iu.c322.trackingservice.model.Order;
import edu.iu.c322.trackingservice.model.UpdateRequest;
import edu.iu.c322.trackingservice.repository.TrackingRepository;
import edu.iu.c322.trackingservice.model.Tracking;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/trackings")
public class TrackingController {


    private TrackingRepository trackingRepository;
    private final WebClient orderService;

    public TrackingController(TrackingRepository trackingRepository, WebClient.Builder builder) {
        this.orderService = builder.baseUrl("http://localhost:8082").build();
        this.trackingRepository = trackingRepository;
    }
//
//    private Tracking itemToTracking(Item item) {
//        Tracking tracking = new Tracking();
//        tracking.setDate(item.date());
//        tracking.setStatus(item.status());
//        return tracking;
//    }
//
//    private Mono<Order> retrieveOrderFromId (int orderId) {
//        return orderService.get()
//                .uri("order/{id}", orderId)
//                .retrieve()
//                .bodyToMono(Order.class);
//    }
//
//    private Item findItemInOrder (Mono<Order> orderMono, int itemId) {
//        return orderMono.block()
//                .items()
//                .stream()
//                .filter(x -> x.id() == itemId)
//                .findAny()
//                .orElse(null);
//    }
//
//    private Item updateStatus (Item item, String status) {
//        return new Item (item.id(),
//                item.name(),
//                item.quantity(),
//                item.price(),
//                status,
//                item.date());
//    }

    @GetMapping("/{orderId}/{itemId}")
    public Tracking getByOrderIdAndItemId(@PathVariable int orderId, @PathVariable int itemId) {
        return trackingRepository.findByOrderIdAndItemId(orderId, itemId);
    }

    @PutMapping("/{orderId}")
    public void updateTrackingStatus(@RequestBody UpdateRequest updateRequest, @PathVariable int orderId) {
        Tracking tracking = trackingRepository.findByOrderIdAndItemId(orderId, updateRequest.getItemId());
        if (tracking != null) {
            tracking.setStatus(updateRequest.getStatus());
            tracking.setDate(LocalDate.now());
        } else {
            tracking = new Tracking();
            tracking.setDate(LocalDate.now());
            tracking.setOrderId(orderId);
            tracking.setItemId(updateRequest.getItemId());
            tracking.setStatus(updateRequest.getStatus());
        }
        trackingRepository.save(tracking);
    }
}
