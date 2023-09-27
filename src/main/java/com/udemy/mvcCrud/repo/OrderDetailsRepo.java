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
    @Query(value = "SELECT * FROM order_details  ORDER BY deadline", nativeQuery = true)
    List<OrderDetails> findSortedByDeadline();

    @Query(value="SELECT * FROM order_details WHERE date_and_time BETWEEN '2023-09-22 10:00:00' AND '2023-09-23 18:00:00' ORDER BY profit_point DESC",nativeQuery = true)
    List<OrderDetails> findByOrderDateBetween();

    @Query(value="SELECT * FROM order_details WHERE status=0 ORDER BY time_required",nativeQuery = true)
    public List<OrderDetails> getOrdersInProcessed();

//    @Query(value = "SELECT profitPoint FROM orderdetails WHERE order_id = :orderID ORDER BY deadline ASC", nativeQuery = true)
//    double findSortedByProfitPoint(@Param("orderID") int orderID);
}
