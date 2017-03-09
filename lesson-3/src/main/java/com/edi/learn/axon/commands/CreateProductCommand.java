package com.edi.learn.axon.commands;

/**
 * Created by Edison Xu on 2017/3/7.
 */
public class CreateProductCommand {

    //@TargetAggregateIdentifier
    // here @TargetAggregateIdentifier annotation is optional because it's a construct command
    private String id;
    private String name;
    private long stock;

    public CreateProductCommand(String id, String name, long stock) {
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

