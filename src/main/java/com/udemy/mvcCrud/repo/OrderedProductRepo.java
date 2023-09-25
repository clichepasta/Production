package com.udemy.mvcCrud.repo;

import com.udemy.mvcCrud.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepo extends JpaRepository<OrderedProduct, Integer> {

    @Query(value = "SELECT SUM(amount) FROM ordered_product WHERE order_id = :orderID", nativeQuery = true)
    double totalAmount(@Param("orderID") int orderID);

    @Query(value = "SELECT SUM(profitamount) FROM ordered_product WHERE order_id = :orderID", nativeQuery = true)
    double totalProfitAmount(@Param("orderID") int orderID);

    @Query(value = "SELECT SUM(time_required) FROM ordered_product WHERE order_id = :orderID", nativeQuery = true)
    double totalTimeRequired(@Param("orderID") int orderID);
}