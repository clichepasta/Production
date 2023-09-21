package com.udemy.mvcCrud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@ToString
public class Customer {

    @Id
    private int customer_id;
    private String customer_name;
    private String company_name;
}
