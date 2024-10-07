package com.example.esomm.demo.ecom.service;

import com.example.esomm.demo.ecom.Entity.Cart;
import com.example.esomm.demo.ecom.Entity.CartItem;
import com.example.esomm.demo.ecom.Entity.Product;
import com.example.esomm.demo.ecom.Entity.User;
import com.example.esomm.demo.ecom.repository.CartRepository;
import com.example.esomm.demo.ecom.repository.ProductRepository;
import com.example.esomm.demo.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart addToCart(Long userId, Long productId, int quantity) {
        // Find the user
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Find the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Create or get the user's cart
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        // Add item to the cart
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);

        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }

    public Cart getCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public void removeCartItem(Long cartItemId) {
        // Logic to remove the cart item, ensure you handle the retrieval of the cart item correctly
    }
}
