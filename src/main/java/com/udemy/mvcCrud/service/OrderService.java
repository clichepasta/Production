package com.udemy.mvcCrud.service;

import com.udemy.mvcCrud.model.OrderDetails;
import com.udemy.mvcCrud.model.OrderedProduct;
import com.udemy.mvcCrud.model.Product;
import com.udemy.mvcCrud.repo.OrderDetailsRepo;
import com.udemy.mvcCrud.repo.OrderedProductRepo;
import com.udemy.mvcCrud.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final ProductRepo productRepo;
    private final OrderDetailsRepo orderDetailsRepo;
    private final OrderedProductRepo  orderedProductRepo;

    @Autowired
    public OrderService(ProductRepo productRepo, OrderDetailsRepo orderDetailsRepo, OrderedProductRepo orderedProductRepo) {
        this.productRepo = productRepo;
        this.orderDetailsRepo = orderDetailsRepo;
        this.orderedProductRepo= orderedProductRepo;
    }


    public List<OrderDetails> getSortedOrderDetails() {
        // Retrieve the sorted list of OrderDetails

        List<OrderDetails> sortedOrderDetails = orderDetailsRepo.findSortedByProfitPoint();
        System.out.println(sortedOrderDetails);
        // Perform additional logic on the sortedOrderDetails if needed

        return sortedOrderDetails;
    }





    public OrderDetails addOrder(OrderDetails orderDetails){
        int orderID= orderDetails.getOrder_id();
        double amount= orderedProductRepo.totalAmount(orderID);
        double profitAmount= orderedProductRepo.totalProfitAmount(orderID);
        double capacity= orderedProductRepo.totalTimeRequired(orderID);
//        double profitPoint=orderDetailsRepo.findSortedByProfitPoint(orderID);
        orderDetails.setAmount(amount);
        double profitPoint=profitAmount/capacity;

        orderDetails.setProfitPoint(profitPoint);
        orderDetails.setProfitAmount(profitAmount);
        orderDetails.setTime_required(capacity);
        orderDetails.setDateAndTime(LocalDateTime.now());
        return orderDetailsRepo.save(orderDetails);
    }

    public OrderedProduct addOrderedProduct(OrderedProduct orderedProduct){
        int productID= orderedProduct.getId().getProductId();
        Product product= productRepo.getOne(productID);
        double price= product.getSelling_price();
        orderedProduct.setAmount(price);
        double capacity = product.getCapacityPerDay();
        orderedProduct.setTimeRequired(capacity);
        double profit= product.getProfit();
        orderedProduct.setProfitamount(profit);
        return orderedProductRepo.save(orderedProduct);
    }

    public List<OrderDetails> findAllOrder(){
        return orderDetailsRepo.findAll();
    }

    public List<OrderedProduct> findAllOrderedProduct(){
        return orderedProductRepo.findAll();
    }

}
