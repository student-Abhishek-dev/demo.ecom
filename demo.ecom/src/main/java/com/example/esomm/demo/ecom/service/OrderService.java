package com.example.esomm.demo.ecom.service;

import com.example.esomm.demo.ecom.Entity.Order;
import com.example.esomm.demo.ecom.Entity.OrderItem;
import com.example.esomm.demo.ecom.Entity.Product;
import com.example.esomm.demo.ecom.Entity.User;
import com.example.esomm.demo.ecom.repository.CartRepository;
import com.example.esomm.demo.ecom.repository.OrderRepository;
import com.example.esomm.demo.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        // Fetch the cart for the user
        var cart = cartRepository.findByUserId(userId).orElseThrow();

        // Create a new order and populate it with cart items
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(cart.getItems().stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum());
        order.setOrderItems(cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            return orderItem;
        }).toList());
        order.setStatus("PROCESSING");

        // Save the order
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
