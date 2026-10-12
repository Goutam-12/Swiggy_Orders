package com.swiggy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiggy.entity.OrderEntity;
import com.swiggy.repository.OrderRepository;
import com.swiggy.request.OrderRequest;
import com.swiggy.response.OrderResponse;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	// CREATE
	public OrderResponse acceptOrder(OrderRequest orderRequest) {
		OrderEntity entity = new OrderEntity();

		entity.setCustomerId(orderRequest.getCustomerId());
		entity.setCustomerName(orderRequest.getCustomerName());
		entity.setFoodName(orderRequest.getFoodName());
		entity.setQuantity(orderRequest.getQuantity());
		entity.setPrice(orderRequest.getPrice());
		entity.setPaymentMehtod(orderRequest.getPaymentMehtod());
		entity.setDeliveryAddress(orderRequest.getDeliveryAddress());

		entity.setOrderId(generetedId());

		double totalAmount = (orderRequest.getPrice() * orderRequest.getQuantity());

		entity.setTotalAmount(totalAmount);

		OrderEntity responseEntity = orderRepository.save(entity);

		OrderResponse orderResponse = new OrderResponse();

		orderResponse.setOrderId(responseEntity.getOrderId());
		orderResponse.setCustomerId(responseEntity.getCustomerId());
		orderResponse.setCustomerName(responseEntity.getCustomerName());
		orderResponse.setTotalAmount(responseEntity.getTotalAmount());
		orderResponse.setPaymentStatus("SUCCESS");
		orderResponse.setOrderStatus("PLACED");

		return orderResponse;

	}
	// READ -> findById
	public OrderResponse getOrderById(int orderId)
	{
		System.out.println("OrderService.getOrderById() :::::::::: START");
		OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
		
		OrderResponse response=new OrderResponse();
		response.setOrderId(orderEntity.getOrderId());
		response.setCustomerId(orderEntity.getCustomerId());
		response.setCustomerName(orderEntity.getCustomerName());
		response.setTotalAmount(orderEntity.getTotalAmount());
		response.setPaymentStatus("SUCCESS");
		response.setOrderStatus("PLACED");
		
		System.out.println("OrderService.getOrderById() ::::::::: END");
		
		return response;
	}
	
	// READ -> findByPaymentMethod
	public List<OrderResponse> getOrderByPaymentType(String paymentType)
	{
		System.out.println("OrderService.getOrderByPaymentType() :::::::::::::: START");
		List<OrderEntity> orderEntity=orderRepository.findByPaymentType(paymentType);
		
		List<OrderResponse> response=new ArrayList<OrderResponse>();
		
		for(OrderEntity entity : orderEntity)
		{
			OrderResponse orderResponse=new OrderResponse();
			orderResponse.setCustomerId(entity.getCustomerId());
			orderResponse.setOrderId(entity.getOrderId());
			orderResponse.setCustomerName(entity.getCustomerName());
			orderResponse.setTotalAmount(entity.getTotalAmount());
			orderResponse.setPaymentStatus("SUCCESS");
			orderResponse.setOrderStatus("PLACED");
			
			response.add(orderResponse);
		}
		System.out.println("OrderService.getOrderByPaymentType() :::::::::::: END");
		return response;
	}
	
	// READ -> findAll records
	public List<OrderEntity> getFindAllRecords()
	{
		List<OrderEntity> orderEntity=(List<OrderEntity>) orderRepository.findAll();
		
		return orderEntity;
	}
	
	public int generetedId() {
		Random random = new Random();

		int id = 10000 + random.nextInt(90000);

		return id;
	}

}
