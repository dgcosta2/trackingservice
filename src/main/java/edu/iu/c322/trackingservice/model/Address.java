package edu.iu.c322.trackingservice.model;

public record Address(
    int id,
    String state,
    String city,
    int postalCode
)
{}
