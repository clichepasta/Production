package com.udemy.mvcCrud.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

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
    private double amount;
    private double profitAmount;
    private int deadline;
    private double time_required;
    private LocalDateTime dateAndTime;
    private int status;
    private double profitPoint;




    //getter setter

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(double profitAmount) {
        this.profitAmount = profitAmount;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public double getTime_required() {
        return time_required;
    }

    public void setTime_required(double time_required) {
        this.time_required = time_required;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public double getProfitPoint() {
        return profitPoint;
    }

    public void setProfitPoint(double profitPoint) {
        this.profitPoint = profitPoint;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
