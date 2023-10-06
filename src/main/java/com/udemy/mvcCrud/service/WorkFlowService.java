package com.udemy.mvcCrud.service;
import com.udemy.mvcCrud.model.*;
import com.udemy.mvcCrud.model.RejectedOrders;
import com.udemy.mvcCrud.repo.OrderDetailsRepo;
import com.udemy.mvcCrud.repo.OrderedProductRepo;
import com.udemy.mvcCrud.repo.ProductRepo;
import com.udemy.mvcCrud.repo.RejectedOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void checkConsecutiveProductWithDeadline(OrderedProduct orderedProduct,double timeRequired, double checkVacant){

        if ( timeRequired<checkVacant){
            orderedProduct.setStatus(1);
            orderedProductRepo.save(orderedProduct);
        }


    }

    public List<OrderedProduct> getWorkFlow() {

//        List<OrderedProduct> workflowList = new ArrayList<>();
        List<OrderedProduct> orderedProducts = orderedProductRepo.getOrderedProductSortedByDeadline(); // Fetch ordered products from the repository
        List<OrderedProduct> finalWorkFlow=new ArrayList<>();
        List<OrderDetails> orderDetails=orderDetailsRepo.findSortedByDeadline();

        List<Product> products= productRepo.getProductList();


        double contraint=0.3;
        double daysUsed=0,deadline,newDeadline=0 , vacant, checkVacant;

        //orderDetails pointer
        int orderSeq=0;

        //Id pointers
        int orderIdPointer = orderDetails.get(0).getOrder_id();
        int productIdPointer=orderedProducts.get(0).getId().getProductId();

        daysUsed=orderedProducts.get(0).getTimeRequired();
        deadline=orderDetails.get(orderSeq).getDeadline();
        vacant=deadline-daysUsed;
        checkVacant=deadline-orderDetails.get(orderSeq).getTime_required();
        System.out.println(orderedProducts.size());

        System.out.println(vacant+"  first vacant");
        System.out.println(checkVacant+"  first checkVacant");
        System.out.println(orderDetails.get(0).getConstraintTime()+"  first constraint time");
        System.out.println(daysUsed + " days used");
        System.out.println(deadline + " deadline");


//        added max orderedProduct
        if(daysUsed<deadline) {

            orderedProducts.get(0).setStatus(1);
            orderedProductRepo.save(orderedProducts.get(0));
            finalWorkFlow.add(orderedProducts.get(0));

        }
        for (int i=1;i<orderedProducts.size();i++){

            if(orderedProducts.get(i).getStatus()==1){

                continue;
            }
            if((orderedProducts.size()-i )< 4){
                orderedProducts.get(i).setStatus(1);
                orderedProductRepo.save(orderedProducts.get(i));
                finalWorkFlow.add(orderedProducts.get(i));

//                productIdPointer = orderedProducts.get(i).getId().getProductId();
                daysUsed = daysUsed + orderedProducts.get(i).getTimeRequired();
continue;

            }

            OrderedProduct currentObj=orderedProductRepo.getOrderedProductByProductId(orderedProducts.get(i).getId().getProductId(),orderedProducts.get(i).getId().getOrderId());
            OrderedProduct executingObj=orderedProductRepo.getOrderedProductByProductId(productIdPointer,orderedProducts.get(i).getId().getOrderId());


            if(currentObj.getId().getOrderId()!=orderIdPointer){
                orderSeq=orderSeq+1;
                newDeadline=orderDetails.get(orderSeq).getDeadline();
//                daysUsed = daysUsed + currentObj.getTimeRequired();
                vacant = newDeadline-deadline+vacant - currentObj.getTimeRequired() ;
                deadline=newDeadline;
                checkVacant=vacant-orderDetails.get(orderSeq).getConstraintTime();

                if(currentObj.getId().getProductId()==executingObj.getId().getProductId()){
                    currentObj.setStatus(1);
                    orderedProductRepo.save(currentObj);
                    finalWorkFlow.add(currentObj);
                    productIdPointer=currentObj.getId().getProductId();
                    orderIdPointer=currentObj.getId().getOrderId();
                    daysUsed = daysUsed + currentObj.getTimeRequired();
                    vacant = vacant - currentObj.getTimeRequired() - productRepo.getChainChangeTime(productIdPointer);



                }
                else {
                    OrderedProduct nextConsecutiveProductObj = orderedProductRepo.getOrderedProductByProductId(productIdPointer, orderDetails.get(orderSeq).getOrder_id());
                    if (nextConsecutiveProductObj.getTimeRequired() < checkVacant) {
                        nextConsecutiveProductObj.setStatus(1);
                        orderedProductRepo.save(nextConsecutiveProductObj);
                        finalWorkFlow.add(nextConsecutiveProductObj);


                        daysUsed = daysUsed + nextConsecutiveProductObj.getTimeRequired();
                        vacant = vacant - nextConsecutiveProductObj.getTimeRequired();

                    }
                    currentObj.setStatus(1);
                    orderedProductRepo.save(currentObj);
                    finalWorkFlow.add(currentObj);

                    productIdPointer = currentObj.getId().getProductId();
                    orderIdPointer=currentObj.getId().getOrderId();
                    daysUsed = daysUsed + currentObj.getTimeRequired();
                    vacant = vacant - currentObj.getTimeRequired() - productRepo.getChainChangeTime(productIdPointer);
                    continue;

                }
            }
        if (currentObj.getId().getOrderId()==executingObj.getId().getOrderId()){
                if (currentObj.getId().getProductId()!= executingObj.getId().getProductId()) {
                    OrderedProduct nextConsecutiveProductObj = orderedProductRepo.getOrderedProductByProductId(productIdPointer, orderDetails.get(orderSeq + 1).getOrder_id());

                    if (nextConsecutiveProductObj.getTimeRequired() < checkVacant) {
                        nextConsecutiveProductObj.setStatus(1);
                        orderedProductRepo.save(nextConsecutiveProductObj);
                        finalWorkFlow.add(nextConsecutiveProductObj);


                        daysUsed = daysUsed + nextConsecutiveProductObj.getTimeRequired();
                        vacant = vacant - nextConsecutiveProductObj.getTimeRequired();

                    }
                    currentObj.setStatus(1);
                    orderedProductRepo.save(currentObj);
                    finalWorkFlow.add(currentObj);

                    productIdPointer = currentObj.getId().getProductId();
                    orderIdPointer=currentObj.getId().getOrderId();
                    daysUsed = daysUsed + currentObj.getTimeRequired();
                    vacant = vacant - currentObj.getTimeRequired() - productRepo.getChainChangeTime(productIdPointer);



                }}


            }





//
//                if(orderedProducts.get(i).getId().getOrderId()!=orderIdPointer){
//                orderIdPointer=orderedProducts.get(i).getId().getOrderId();
//                orderSeq=orderSeq+1;
////                OrderedProduct currentObj=orderedProductRepo.getOrderedProductByProductId(productIdPointer,orderIdPointer);
//
//
//
//
//
//
//                currentObj.setStatus(1);
//                orderedProductRepo.save(currentObj);
//                finalWorkFlow.add(currentObj);
//
//
//                daysUsed=daysUsed+currentObj.getTimeRequired();
//                newDeadline=orderDetails.get(orderSeq+1).getDeadline();
//                vacant=newDeadline-deadline+vacant- currentObj.getTimeRequired();
//                checkVacant=newDeadline-deadline+vacant-orderDetails.get(orderSeq).getTime_required()-orderDetails.get(orderSeq).getTime_required();
//                vacant=newDeadline-deadline+vacant- currentObj.getTimeRequired();
//
//                System.out.println(vacant+ "vacant");
//                System.out.println(checkVacant+ " checkvacant");
//
//            }
//
////            OrderedProduct currentObj=orderedProductRepo.getOrderedProductByProductId(orderedProducts.get(i).getId().getProductId(),orderedProducts.get(i).getId().getOrderId());
////            OrderedProduct executingObj=orderedProductRepo.getOrderedProductByProductId(productIdPointer,orderedProducts.get(i).getId().getOrderId());
//
//
//            System.out.println(nextConsecutiveProductObj.getTimeRequired()+ "next consecutiveOrder");
//            System.out.println(checkVacant-executingObj.getTimeRequired()-orderDetails.get(orderSeq+1).getConstraintTime());
//
//            if(executingObj!=currentObj){
//                currentObj.setStatus(1);
//                orderedProductRepo.save(currentObj);
//                finalWorkFlow.add(currentObj);
//
//                daysUsed=daysUsed+nextConsecutiveProductObj.getTimeRequired();
//                vacant=vacant- currentObj.getTimeRequired();
//
//
//
//
//            }
//
//
//            //debug check lines
////            double x=products.get(i).getChain_change_time();
////            double y=orderDetails.get(orderSeq).getDeadline();
////            System.out.println(x);
//            System.out.println(orderedProducts.get(i).getId().getOrderId());
//            System.out.println(orderedProducts.get(i).getId().getProductId());
//            System.out.println(vacant+ " Vacant");
//            System.out.println(checkVacant+ " checkVacant");
//            System.out.println(daysUsed+" days used");
//            System.out.println(newDeadline+" newDeadline");
//            System.out.println(orderSeq+" orderSEQ");
////            System.out.println(nextConsecutiveProductObj.getId().getProductId());
////            System.out.println(orderedProducts.size());


        System.out.println(finalWorkFlow.size());
        System.out.println(daysUsed);

        return finalWorkFlow;
//        return orderDetailsRepo.findSortedByDeadline();
    }
}