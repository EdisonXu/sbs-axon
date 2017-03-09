package com.edi.learn.axon.domain;

/**
 * Created by Edison on 2017/3/9.
 */
public class Product {
    private String id;
    private String name;
    private long stock;

    public Product(String id, String name, long stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getStock() {
        return stock;
    }
}
