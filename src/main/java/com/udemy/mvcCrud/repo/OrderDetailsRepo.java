package com.udemy.mvcCrud.repo;

import com.udemy.mvcCrud.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {

<<<<<<< HEAD
    @Query(value="SELECT * FROM order_details WHERE date_and_time BETWEEN '2023-09-25 10:00:00' AND '2023-09-26 18:00:00' ORDER BY profit_point DESC",nativeQuery = true)
=======
    @Query(value="SELECT * FROM order_details WHERE date_and_time BETWEEN '2023-09-22 10:00:00' AND '2023-09-23 18:00:00' ORDER BY profit_point DESC",nativeQuery = true)
>>>>>>> 7cf93a5 (first commit)
    List<OrderDetails> findByOrderDateBetween();

    @Query(value="SELECT * FROM order_details WHERE status=0 ORDER BY time_required",nativeQuery = true)
    public List<OrderDetails> getOrdersInProcessed();

<<<<<<< HEAD
    @Query(value="SELECT * FROM order_details WHERE status=-1 AND date_and_time BETWEEN '2023-09-25 10:00:00' AND '2023-09-26 18:00:00' ORDER BY acceptance_value",nativeQuery = true)
    List<OrderDetails> findByOrderDateBetweenRejected();

=======
>>>>>>> 7cf93a5 (first commit)
//    List<OrderDetails> getOrdersBetweenDates(LocalDateTime startDate,LocalDateTime endDate);

}
