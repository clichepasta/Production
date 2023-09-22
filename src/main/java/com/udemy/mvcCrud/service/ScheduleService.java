package com.udemy.mvcCrud.service;

import com.udemy.mvcCrud.repo.OrderDetailsRepo;
import com.udemy.mvcCrud.repo.OrderedProductRepo;
import com.udemy.mvcCrud.repo.ProductRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ProductRepo productRepo;
    private final OrderDetailsRepo orderDetailsRepo;
    private final OrderedProductRepo orderedProductRepo;

    public ScheduleService(ProductRepo productRepo, OrderDetailsRepo orderDetailsRepo, OrderedProductRepo orderedProductRepo) {
        this.productRepo = productRepo;
        this.orderDetailsRepo = orderDetailsRepo;
        this.orderedProductRepo = orderedProductRepo;
    }

    @Scheduled(fixedRate = 6000) // 60 seconds = 1 minute
    public void performScheduledTask() {
        // task logic

        System.out.println("Scheduled task executed at: " + java.time.LocalDateTime.now());
    }
}
