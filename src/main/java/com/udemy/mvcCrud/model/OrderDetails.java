package com.udemy.mvcCrud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "OrderDetails")
@Getter
@Setter
@ToString
public class OrderDetails {

    public OrderDetails() {
    }

    @Id
    private int order_id;

    private int customer_id;

    private int amount;

    private int product_id;

    private double amountByTime;
    private int profit;
    private int deadline;
    private int time_required;



}
