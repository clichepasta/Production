package com.udemy.mvcCrud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Product")
@ToString
public class Product {

    @Id
    private int product_id;
    private String product_name;

    private int capacityPerDay;
    private double manufacture_price;
    private double selling_price;
    private double profit;

    public Product(int product_id, String product_name, int capacityPerDay, double manufacture_price, double selling_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.capacityPerDay = capacityPerDay;
        this.manufacture_price = manufacture_price;
        this.selling_price = selling_price;
        this.profit=selling_price-manufacture_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getCapacityPerDay() {
        return capacityPerDay;
    }

    public Product() {
    }

    public void setCapacityPerDay(int capacityPerDay) {
        this.capacityPerDay = capacityPerDay;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getManufacture_price() {
        return manufacture_price;
    }

    public void setManufacture_price(double manufacture_price) {
        this.manufacture_price = manufacture_price;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }

    public double getProfit() {
        return selling_price - manufacture_price;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }



    public void setProfit() {
        this.profit = getProfit();
    }

}
