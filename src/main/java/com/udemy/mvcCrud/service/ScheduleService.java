package com.udemy.mvcCrud.service;


import com.udemy.mvcCrud.model.OrderDetails;
import com.udemy.mvcCrud.model.OrderManufacturing;
import com.udemy.mvcCrud.repo.OrderDetailsRepo;
import com.udemy.mvcCrud.repo.OrderedProductRepo;
import com.udemy.mvcCrud.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.udemy.mvcCrud.repo.OrderDetailsRepo;
import com.udemy.mvcCrud.repo.OrderedProductRepo;
import com.udemy.mvcCrud.repo.ProductRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduleService {

    private final ProductRepo productRepo;
    private final OrderDetailsRepo orderDetailsRepo;
    private final OrderedProductRepo orderedProductRepo;

    private final OrderService orderService;

    public ScheduleService(ProductRepo productRepo, OrderDetailsRepo orderDetailsRepo, OrderedProductRepo orderedProductRepo, OrderService orderService) {
        this.productRepo = productRepo;
        this.orderDetailsRepo = orderDetailsRepo;
        this.orderedProductRepo = orderedProductRepo;
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 600000) // 60 seconds = 1 minute
    public void performScheduledTask() {
        // task logic
        List<OrderDetails> ordersBetweenDates =  getOrdersBetweenDates();

        //declaration
        double time_used,deadline,vacant;
        int order_id,i=0, accepted=1, rejected=-1,id;
        List<OrderManufacturing> orderManufacturing= new ArrayList<>();

        //initial order with max profit
        order_id= ordersBetweenDates.get(0).getOrder_id();
        time_used= ordersBetweenDates.get(0).getTime_required();
        deadline= ordersBetweenDates.get(0).getDeadline();
        vacant= deadline-time_used;
        OrderManufacturing newOrderManufacturing = new OrderManufacturing(order_id, time_used, deadline);
        orderManufacturing.add(newOrderManufacturing);
        System.out.println("Accepted0: " + ordersBetweenDates.get(0).getOrder_id()+ " - "+time_used +" / "+deadline);

        //logic 1 for order acceptance
        for(OrderDetails orderDetails: ordersBetweenDates ){
            int currentOrderId = ordersBetweenDates.get(i+1).getOrder_id();
            double next_requiredTime = ordersBetweenDates.get(i+1).getTime_required();
            double next_deadline = ordersBetweenDates.get(i+1).getDeadline();
            if(i>0){
                if(next_deadline<deadline){
                    double specificDeadline_requiredTime=next_requiredTime;
                    for(OrderManufacturing j: orderManufacturing){
                        if(j.getDeadline()==next_deadline){
                            specificDeadline_requiredTime = specificDeadline_requiredTime+j.getTime_required();
                        }
                    }
                    if(next_requiredTime<=vacant && specificDeadline_requiredTime<=next_deadline){
                        time_used=time_used+next_requiredTime;
                        vacant=vacant-next_requiredTime;

                        //updateStatus
                        Optional<OrderDetails> currentOrder = orderDetailsRepo.findById(currentOrderId);
                        id=orderDetails.getOrder_id();
                        orderService.updateStatus(accepted,id);
                        OrderManufacturing newOrderManufacturing1 = new OrderManufacturing(currentOrderId, next_requiredTime, next_deadline);
                        orderManufacturing.add(newOrderManufacturing1);
                        System.out.println("Accepted1: " + ordersBetweenDates.get(i).getOrder_id() +" - " +time_used +" / "+deadline);
                    }
                    else{
                        //updateStatus
                        Optional<OrderDetails> currentOrder = orderDetailsRepo.findById(currentOrderId);
                        id=orderDetails.getOrder_id();
                        orderService.updateStatus(rejected,id);
                        System.out.println("Rejected1: "+ ordersBetweenDates.get(i).getOrder_id()+" - " + time_used +" / "+deadline);
                    }
                }
                else{
                    double checkVacant= next_deadline - deadline + vacant;
                    if(next_requiredTime<=checkVacant){
                        deadline=next_deadline;
                        time_used=time_used+next_requiredTime;
                        vacant=deadline-time_used;

                        //updateStatus
                        Optional<OrderDetails> currentOrder = orderDetailsRepo.findById(currentOrderId);
                        id=orderDetails.getOrder_id();
                        orderService.updateStatus(accepted,id);
                        OrderManufacturing newOrderManufacturing2 = new OrderManufacturing(currentOrderId, next_requiredTime, next_deadline);
                        orderManufacturing.add(newOrderManufacturing2);
                        System.out.println("Accepted2:" + ordersBetweenDates.get(i).getOrder_id()+" - "  + time_used +" / " +deadline);
                    }
                    else{
                        //updateStatus
                        Optional<OrderDetails> currentOrder = orderDetailsRepo.findById(currentOrderId);
                        id=orderDetails.getOrder_id();
                        orderService.updateStatus(rejected,id);
                        System.out.println("Rejected2:" + ordersBetweenDates.get(i).getOrder_id()+" - "  + time_used +" / "+deadline);
                    }
                }
            }
            else{
                //updateStatus
                Optional<OrderDetails> currentOrder = orderDetailsRepo.findById(currentOrderId);
                id=orderDetails.getOrder_id();
                orderService.updateStatus(accepted,id);
            }
            i++;
        }

        //logic 2 for order acceptance -

    }

    public List<OrderDetails> getOrdersBetweenDates() {
        return orderDetailsRepo.findByOrderDateBetween();
    }






}

