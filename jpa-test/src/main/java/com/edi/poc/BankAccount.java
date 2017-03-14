package com.edi.poc;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Edison Xu on 2017/3/10.
 */
@Entity
public class BankAccount implements Serializable{

    private static final long serialVersionUID = -591341015965695559L;

    private AccountId id;

    private String name;

    public BankAccount() {
    }

    public BankAccount(AccountId id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = new AccountId(id);
    }

    @Column
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
