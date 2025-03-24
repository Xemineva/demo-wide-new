package com.example.demo.controller;

import com.example.demo.entity.OrderCart;
import com.example.demo.service.OrderCartService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-cart")
public class OrderCartController {
    @Autowired
    private OrderCartService orderCartService;

    @GetMapping("/{cartId}")
    public Optional<OrderCart> getOrderCart(@PathVariable Long cartId) {
        return orderCartService.getOrderCartById(cartId);
    }

    @PostMapping
    public OrderCart createOrderCart(@RequestBody OrderCart orderCart) {
        return orderCartService.saveOrderCart(orderCart);
    }

    @PostMapping("/{cartId}/add")
    public OrderCart addProductToCart(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        return orderCartService.addProductToCart(cartId, productId, quantity);
    }
}
