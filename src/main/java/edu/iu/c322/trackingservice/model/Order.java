package edu.iu.c322.trackingservice.model;

import java.time.LocalDate;
import java.util.List;

public record Order (
        int id,
        int customerId,
        double total,
        Address shippingAddress,
        List<Item> items,
        Payment payment,
        LocalDate orderPlaced
) {}
