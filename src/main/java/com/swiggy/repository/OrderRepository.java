package com.swiggy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.swiggy.entity.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer>
{
	// Derived Method
	// Read->findById
	public OrderEntity findByOrderId(int orderId);
	
	// Read->findByPaymentMethod
	@Query("select o from OrderEntity o where o.paymentMehtod = :type and o.quantity > 3") // JPQL Query
	public List<OrderEntity> findByPaymentType(@Param("type") String paymentType);
}
