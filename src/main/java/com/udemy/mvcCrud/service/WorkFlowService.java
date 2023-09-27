package com.udemy.mvcCrud.service;
import com.udemy.mvcCrud.model.RejectedOrders;
import com.udemy.mvcCrud.model.OrderDetails;
import com.udemy.mvcCrud.model.RejectedOrders;
import com.udemy.mvcCrud.repo.OrderDetailsRepo;
import com.udemy.mvcCrud.repo.OrderedProductRepo;
import com.udemy.mvcCrud.repo.ProductRepo;
import com.udemy.mvcCrud.repo.RejectedOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkFlowService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Autowired
    OrderedProductRepo orderedProductRepo;

    @Autowired
    private final RejectedOrdersRepo rejectedOrdersRepo;

    @Autowired
    public WorkFlowService(ProductRepo productRepo, OrderDetailsRepo orderDetailsRepo, OrderedProductRepo orderedProductRepo, RejectedOrdersRepo rejectedOrdersRepo) {
        this.productRepo = productRepo;
        this.orderDetailsRepo = orderDetailsRepo;
        this.orderedProductRepo = orderedProductRepo;
        this.rejectedOrdersRepo = rejectedOrdersRepo;
    }

    public List<OrderDetails> getRejectedOrders() {
        return rejectedOrdersRepo.findRejecteedOrders();
//        return orderDetailsRepo.findSortedByDeadline();
    }
}