package com.udemy.mvcCrud.service;

import com.udemy.mvcCrud.model.OrderDetails;
import com.udemy.mvcCrud.model.OrderedProduct;
import com.udemy.mvcCrud.model.Product;
import com.udemy.mvcCrud.repo.OrderDetailsRepo;
import com.udemy.mvcCrud.repo.OrderedProductRepo;
import com.udemy.mvcCrud.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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


    public List<OrderDetails> getUpdatedList() {
        // Retrieve the sorted list of OrderDetails
        double vacant, y1,y2,x;

        List<OrderDetails> sortedOrderDetails = orderDetailsRepo.findSortedByProfitPoint();
        List<OrderDetails> updatedOrderDetailsList = new ArrayList<>();

        for (OrderDetails orderDetail : sortedOrderDetails) {
            // Set the status field to 1

            orderDetail.setStatus(1);

            // Add the modified OrderDetails to the new list
            updatedOrderDetailsList.add(orderDetail);
        }

        System.out.println(updatedOrderDetailsList);

        // Perform additional logic on the sortedOrderDetails if needed

        return updatedOrderDetailsList;
    }

    @Transactional
    public void updateOrderStatus(OrderDetails orderDetails) {
        try {
            // Retrieve the OrderDetails from the database by its order_id
            OrderDetails existingOrder = orderDetailsRepo.findById(orderDetails.getOrder_id()).orElse(null);

            if (existingOrder != null) {
                // Update the status to 1
                existingOrder.setStatus(1);

                // Save the updated OrderDetails to the database
                orderDetailsRepo.save(existingOrder);
            } else {
                // Handle the case where the orderDetails with the given order_id doesn't exist
                // You can throw an exception or handle it as needed
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during the update operation
            // You can log the error or handle it according to your application's requirements
        }
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
