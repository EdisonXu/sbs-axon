package com.edi.learn.axon.common.domain;

/**
 * Created by Edison Xu on 2017/3/9.
 */
public class Order {

    private OrderId id;
    private String username;
    private String productId;
    private long productAmount;
    private long productPrice;
    private long payment;

    public Order(OrderId id, String username, String productId, long productAmount, long productPrice) {
        this.id = id;
        this.username = username;
        this.productId = productId;
        this.productAmount = productAmount;
        this.productPrice = productPrice;
    }

    public OrderId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getProductId() {
        return productId;
    }

    public long getProductAmount() {
        return productAmount;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public long getPayment() {
        return payment;
    }
}
