package com.edi.learn.axon.common.domain;

/**
 * Created by Edison on 2017/3/9.
 */
public class OrderProduct {
    private String id;
    private String name;
    private long price;
    private int amount;

    public OrderProduct(String id, String name, long price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
