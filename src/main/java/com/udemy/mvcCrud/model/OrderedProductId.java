package com.udemy.mvcCrud.model;
import jakarta.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class OrderedProductId implements Serializable {
    private int orderId;
    private int productId;


    // Constructors, getters, and setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}