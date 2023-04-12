package edu.iu.c322.trackingservice.model;

public record Payment (
    int id,
    String method,
    String number,
    Address billingAddress
) {}
