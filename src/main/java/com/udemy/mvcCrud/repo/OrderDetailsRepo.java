package com.udemy.mvcCrud.repo;

import com.udemy.mvcCrud.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {

    @Query(value = "SELECT * FROM order_details  ORDER BY profit_point DESC", nativeQuery = true)
    List<OrderDetails> findSortedByProfitPoint();

//    @Query(value = "SELECT profitPoint FROM orderdetails WHERE order_id = :orderID ORDER BY deadline ASC", nativeQuery = true)
//    double findSortedByProfitPoint(@Param("orderID") int orderID);
}
