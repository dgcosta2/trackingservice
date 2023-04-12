package edu.iu.c322.trackingservice.model;

import java.time.LocalDate;

public record Item (
    int id,
    String name,
    int quantity,
    double price,
    String status,
    LocalDate date
) {}
