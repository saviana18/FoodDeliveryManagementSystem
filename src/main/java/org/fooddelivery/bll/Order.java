package org.fooddelivery.bll;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {
    private int orderId;
    private int clientId;
    private LocalDateTime orderDate;
    private String date;
    private String client;
    private List<String> order;
    private int price;
    private int startHour;
    private int endHour;

    public Order(String client, List<String> order, int price, String date) {
        this.date = date;
        this.client = client;
        this.order = order;
        this.price = price;
    }

    public Order(int orderId, int clientId, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
    }

    public Order() {

    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String clientUsername) {
        this.client = clientUsername;
    }

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> orderedProducts) {
        this.order = orderedProducts;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int totalPrice) {
        this.price = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && clientId == order.clientId && orderDate.equals(order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, date);
    }
}
