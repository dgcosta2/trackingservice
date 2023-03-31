package edu.iu.c322.trackingservice.model;

import java.util.Objects;

public class Address {

    private String state;
    private String city;
    private int postalCode;

    public Address(String state, String city, int postalCode) {
        this.state = state;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return postalCode == address.postalCode && state.equals(address.state) && city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, city, postalCode);
    }
}
