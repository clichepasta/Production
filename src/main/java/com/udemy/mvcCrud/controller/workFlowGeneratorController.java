package com.udemy.mvcCrud.controller;

import com.udemy.mvcCrud.model.OrderDetails;
import com.udemy.mvcCrud.model.OrderStatus;
import com.udemy.mvcCrud.service.WorkFlowGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/workFlowGenerator")
public class workFlowGeneratorController {

      private final WorkFlowGeneratorService workFlowGeneratorService;

    public workFlowGeneratorController(WorkFlowGeneratorService workFlowGeneratorService) {
        this.workFlowGeneratorService = workFlowGeneratorService;
    }

    @PostMapping("/")
    public ResponseEntity<?> workFlowGenerator(@RequestBody int budget, int deadline){
        workFlowGeneratorService.WorkFlowGeneratorService(budget, deadline);
        return  new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<OrderStatus> workFlowGenerator(){
        OrderStatus orders=workFlowGeneratorService.WorkFlowGeneratorService();
        return  new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }
//    @GetMapping("/all")
//    public ResponseEntity<List<OrderDetails>> getAllOrders(){
//        List<OrderDetails> orders = orderService.findAllOrder();
//        return  new ResponseEntity<>(orders, HttpStatus.OK);
//    }
}
