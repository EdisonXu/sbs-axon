package com.edi.learn.axon.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Edison Xu on 2017/3/7.
 */
public class CreateProductCommand {

    // here @TargetAggregateIdentifier annotation is optional because it's a construct command
    // but if using DistributeCommandBus, @TargetAggregateIdentifier must be set!
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private long price;
    private int stock;

    public CreateProductCommand() {
    }

    public CreateProductCommand(String id, String name, long price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

