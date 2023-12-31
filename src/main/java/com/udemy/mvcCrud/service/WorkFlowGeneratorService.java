package com.udemy.mvcCrud.service;

import com.udemy.mvcCrud.model.OrderDetails;
import com.udemy.mvcCrud.model.OrderStatus;
import com.udemy.mvcCrud.model.ProductManufacturing;
import com.udemy.mvcCrud.repo.OrderDetailsRepo;
import com.udemy.mvcCrud.repo.OrderedProductRepo;
import com.udemy.mvcCrud.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkFlowGeneratorService {
    private final ProductRepo productRepo;
    private final OrderService orderService;
    private final OrderDetailsRepo orderDetailsRepo;

    private final OrderedProductRepo orderedProductRepo;
    private final ScheduleService scheduleService;

    public WorkFlowGeneratorService(ProductRepo productRepo, OrderService orderService, OrderDetailsRepo orderDetailsRepo, OrderedProductRepo orderedProductRepo, ScheduleService scheduleService) {
        this.productRepo = productRepo;
        this.orderService = orderService;
        this.orderDetailsRepo = orderDetailsRepo;
        this.orderedProductRepo = orderedProductRepo;
        this.scheduleService = scheduleService;
    }

    public void WorkFlowGeneratorService(int budget, int deadline) {
        double expenditureCapacity = budget / deadline;

    }


    public OrderStatus WorkFlowGeneratorService() {
        OrderStatus orders = scheduleService.performScheduledTask();
        System.out.println(orders);
        List<OrderDetails> getOrdersInProcessed = getOrdersInProcessed();
        return orders;

    }


    public void WorkFlowGeneratorService(int deadline) {
        List<OrderDetails> getOrdersInProcessed = getOrdersInProcessed();
        List<ProductManufacturing> productManufacturing;

    }

    public List<OrderDetails> getOrdersInProcessed() {
        return orderDetailsRepo.getOrdersInProcessed();
    }
}
