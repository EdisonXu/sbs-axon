package com.edi.learn.axon.query.entries;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Remember to add {@code EntityScan }annotation in your start application.
 * <br>
 * Meanwhile, you need to add some packages in Axon Framework to make it work.
 * <br>
 * Created by Edison Xu on 2017/3/14.
 */
@Entity
@Document(collection = "product_entry")
public class ProductEntry {

    @Id
    @org.springframework.data.annotation.Id
    private String id;
    @Column
    private String name;
    @Column
    private long stock;

    public ProductEntry() {
    }

    public ProductEntry(String id, String name, long stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }
}
