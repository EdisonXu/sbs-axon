package com.edi.poc;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Edison Xu on 2017/3/10.
 */
@Entity
public class BankAccount implements Serializable{

    private static final long serialVersionUID = -591341015965695559L;

    @EmbeddedId
    private AccountId id;
    @Column
    private String name;

    public BankAccount() {
    }

    public BankAccount(AccountId id, String name) {
        this.id = id;
        this.name = name;
    }

    public AccountId getId() {
        return id;
    }

    public void setId(AccountId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
