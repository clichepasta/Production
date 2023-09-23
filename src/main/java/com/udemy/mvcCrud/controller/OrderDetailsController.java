package com.udemy.mvcCrud.controller;

import com.udemy.mvcCrud.model.OrderDetails;
import com.udemy.mvcCrud.model.OrderedProduct;
import com.udemy.mvcCrud.model.Product;
import com.udemy.mvcCrud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderDetailsController {

    private final OrderService orderService;

    @Autowired
    public OrderDetailsController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<OrderDetails>> getAllOrders(){
        List<OrderDetails> orders = orderService.findAllOrder();
//        List<OrderDetails> demo = orderService.getSortedOrderDetails();

        return  new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<OrderDetails> addOrders(@RequestBody OrderDetails orderDetails){
        OrderDetails new_order = orderService.addOrder(orderDetails);
        return  new ResponseEntity<>(new_order, HttpStatus.CREATED);
    }



    @GetMapping("/orderedProduct")
    public ResponseEntity<List<OrderedProduct>>  getOrderedProduct(){
        List<OrderedProduct> orderedProducts = orderService.findAllOrderedProduct();
        return  new ResponseEntity<>(orderedProducts, HttpStatus.CREATED);
    }

    @PostMapping("/orderedProduct")
    public ResponseEntity<OrderedProduct> addOrderedProduct(@RequestBody OrderedProduct orderedProduct){
        OrderedProduct new_ordered = orderService.addOrderedProduct(orderedProduct);
        return  new ResponseEntity<>(new_ordered, HttpStatus.CREATED);
    }
}
