package com.swiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.entity.OrderEntity;
import com.swiggy.request.OrderRequest;
import com.swiggy.response.OrderResponse;
import com.swiggy.service.OrderService;

@RestController
@RequestMapping("/orders/")
public class OrderController {

	
	@Autowired
	OrderService orderService;

	// CREATE
	@PostMapping("/place")
	public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest)
	{
		System.out.println("OrderController.placeOrder() : START");
		
		OrderResponse response=orderService.acceptOrder(orderRequest);
		
		System.out.println("OrderController.placeOrder() : END");
		
		return response;
	}
	
	// READ -> findById
	@GetMapping("findById")
	public OrderResponse findOrderById(@RequestParam("id") int id)
	{
		return orderService.getOrderById(id);
	}
	
	// READ -> findByPaymentMethod
	@GetMapping("findByPaymentMethod")
	public List<OrderResponse> findOrderByPaymentMethod(@RequestParam("paymentType") String paymentType)
	{
		return orderService.getOrderByPaymentType(paymentType);
	}
	
	// READ -> findAll records
	@GetMapping("findAllRecord")
	public List<OrderEntity> findByAllRecords()
	{
		List<OrderEntity> orderEntity=orderService.getFindAllRecords();
		return orderEntity;
	}
	
}
