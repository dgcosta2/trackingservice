package edu.iu.c322.trackingservice.repository;

import edu.iu.c322.trackingservice.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public OrderRepository () {
        Order order = new Order();
        Item item1 = new Item(1, "Tea", 24.84, "shipped");
        Item item2 = new Item(2, "Turmeric Paste", 22.99, "ordered");
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        Address address = new Address("Indiana", "Bloomington", 47408);
        Payment payment = new Payment("Discover", "12345678", address);
        order.setReturnRequests(new ArrayList<>());
        order.setItems(items);
        order.setPayment(payment);
        order.setCustomerId(1);
        order.setOrderPlaced("3/4/2023");
        order.setShippingAddress(address);
        order.setTotal(60.26);
        create(order);
    }

    public List<Order> findAll() {
        return orders;
    }

    public int create(Order order) {
        int orderId = orders.size() + 1;
        order.setId(orderId);
        order.setReturnRequests(new ArrayList<>());
        order.setStatus("active");
        int itemId = 1;
        for (Item item : order.getItems()) {
            item.setId(itemId++);
        }
        orders.add(order);
        return orderId;
    }

    public void delete(int orderId) {
        Order o = getByOrderId(orderId);
        if (o != null) {
            o.setStatus("cancelled");
        } else {
            throw new IllegalStateException("Order id is not valid.");
        }
    }

    public void update(ReturnRequest returnRequest) {
        Order o = getByOrderId(returnRequest.getOrderId());
        if (o != null) { //If the order exists in repository
            Item i = getByItemId(o, returnRequest.getItemId());
            if (i != null) {
                if (o.getReturnRequests() == null) {
                    o.setReturnRequests(new ArrayList<>());
                }
                o.getReturnRequests().add(returnRequest);
            } else {
                throw new IllegalStateException("Item id is not valid");
            }
        } else {
            throw new IllegalStateException("Order id is not valid.");
        }
    }

    public List<Order> getByCustomerId (int id) {
        List<Order> orders = findByCustomerId(id);
        if (!orders.isEmpty()) {
            return orders;
        } else {
            throw new IllegalStateException("Orders not found with this id.");
        }
    }

    public Tracking getTrackingByOrderAndItemId(int orderId, int itemId) {
        Order o = getByOrderId(orderId);
        if (o != null) {
            Item item = getByItemId(o, itemId);
            if (item != null) {
                Tracking tracking = new Tracking();
                tracking.setStatus(item.getStatus());
                tracking.setDate(o.getOrderPlaced());
                return tracking;
            } else {
                throw new IllegalStateException("Item with this id does not exist in the system.");
            }
        } else {
            throw new IllegalStateException("Order with this id does not exist in the system.");
        }
    }

    public void updateTrackingStatus(UpdateRequest updateRequest, int orderId) {
        Order o = getByOrderId(orderId);
        if (o != null) {
            Item item = getByItemId(o, updateRequest.getItemId());
            if (item != null) {
                item.setStatus(updateRequest.getStatus());
            } else {
                throw new IllegalStateException("Item with this id does not exist in the system.");
            }
        } else {
            throw new IllegalStateException("Order with this id does not exist in the system.");
        }
    }

    public Item getByItemId(Order order, int id) {
        return order.getItems().stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    public Order getByOrderId(int orderId) {
        return orders.stream().filter(x -> x.getId() == orderId).findAny().orElse(null);
    }

    public List<Order> findByCustomerId(int customerId) {
        return orders.stream().filter(x -> x.getCustomerId() == customerId).toList();
    }
}
