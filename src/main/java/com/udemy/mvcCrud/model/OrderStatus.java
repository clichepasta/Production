package com.udemy.mvcCrud.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OrderStatus {

    private List<Integer> accepted = new ArrayList<>();
    private List<Integer> acceptedWithNewDeadline = new ArrayList<>();

    //getter and setter

    public List<Integer> getAccepted() {
        return accepted;
    }

    public List<Integer> getAcceptedWithNewDeadline() {
        return acceptedWithNewDeadline;
    }
    public void setAccepted(int order_id,int i) {
        accepted.add(order_id);
    }

    public void setAcceptedWithNewDeadline(int order_id, int i) {
       acceptedWithNewDeadline.add(order_id);
    }
}
