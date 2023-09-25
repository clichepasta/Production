package com.udemy.mvcCrud.service;

import com.udemy.mvcCrud.model.OrderDetails;
import com.udemy.mvcCrud.model.Product;
import com.udemy.mvcCrud.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {


    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public OrderDetails addOrder(OrderDetails orderDetails){
        return orderRepo.save(orderDetails);}

    public List<OrderDetails> findAllOrder(){
        return orderRepo.findAll();
    }

}
