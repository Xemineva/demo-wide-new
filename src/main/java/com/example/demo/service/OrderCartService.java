package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.demo.entity.OrderCart;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderCartRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class OrderCartService {
    @Autowired
    private OrderCartRepository orderCartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Optional<OrderCart> getOrderCartById(Long cartId) {
        return orderCartRepository.findById(cartId);
    }

    public OrderCart saveOrderCart(OrderCart orderCart) {
        return orderCartRepository.save(orderCart);
    }

    public OrderCart addProductToCart(Long cartId, Long productId, Integer quantity) {
        Optional<OrderCart> optionalCart = orderCartRepository.findById(cartId);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalCart.isPresent() && optionalProduct.isPresent()) {
            OrderCart cart = optionalCart.get();
            Product product = optionalProduct.get();

            // Create a new OrderItem
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setTotalPrice(product.getPrice() * quantity);

            // Add the item to the cart
            cart.getItems().add(item);
            cart.setTotalPrice(cart.getTotalPrice() + item.getTotalPrice());

            // Save the cart
            return orderCartRepository.save(cart);
        } else {
            throw new RuntimeException("Cart or Product not found");
        }
    }
}