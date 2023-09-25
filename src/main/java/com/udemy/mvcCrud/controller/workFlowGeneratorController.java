package com.udemy.mvcCrud.controller;

import com.udemy.mvcCrud.service.WorkFlowGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
