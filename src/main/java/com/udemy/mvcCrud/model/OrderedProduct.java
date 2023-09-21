package com.udemy.mvcCrud.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "OrderedProduct")
@Getter
@Setter
@ToString
public class OrderedProduct {
    @EmbeddedId
    private OrderedProductId id;
    private int quantity;
    private double amount;
    private double profitamount;


    //getter and setter

    public OrderedProductId getId() {
        return id;
    }

    public void setId(OrderedProductId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double price) {
        this.amount = price*getQuantity();
    }

    public double getProfitamount() {
        return profitamount;
    }

    public void setProfitamount(double profit) {
        this.profitamount = profit*getQuantity();
    }
}

