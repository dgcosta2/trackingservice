package edu.iu.c322.trackingservice.model;

import java.util.Objects;

public class Item {

    private int id;
    private String item;
    private double price;
    private String status;

    public Item(int id, String item, double price, String status) {
        this.id = id;
        this.item = item;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item1 = (Item) o;
        return id == item1.id && Double.compare(item1.price, price) == 0 && item.equals(item1.item) && status.equals(item1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, price, status);
    }
}
