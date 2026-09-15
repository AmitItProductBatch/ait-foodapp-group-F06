package com.ait.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.CartItemDto;
import com.ait.app.exception.CartItemServiceException;
import com.ait.app.model.Cart;
import com.ait.app.model.CartItem;
import com.ait.app.model.FoodItem;
import com.ait.app.repository.CartItemRepository;
import com.ait.app.repository.CartRepository;
import com.ait.app.repository.FoodItemRepository;
import com.ait.app.service.CartItemService;

@Service
public class CartItemServiceimpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public CartItemDto saveCartItem(CartItemDto cartItemDto) {

        
        Optional<Cart> cartOptional =
                cartRepository.findById(cartItemDto.getCartId());

        if (cartOptional.isEmpty()) {
            throw new CartItemServiceException(
                    "Cart not found",
                    HttpStatus.NOT_FOUND
            );
        }

        Cart cart = cartOptional.get();

        
        Optional<FoodItem> foodItemOptional =
                foodItemRepository.findById(cartItemDto.getFoodItemId());

        if (foodItemOptional.isEmpty()) {
            throw new CartItemServiceException(
                    "Food item not found",
                    HttpStatus.NOT_FOUND
            );
        }

        FoodItem foodItem = foodItemOptional.get();

        
        if (cartItemDto.getQuantity() <= 0) {
            throw new CartItemServiceException(
                    "Quantity must be greater than 0",
                    HttpStatus.BAD_REQUEST
            );
        }

      
        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setFooditem(foodItem);
        cartItem.setQuantity(cartItemDto.getQuantity());

        
        cartItem.setPrice(foodItem.getPrice());

        
        int total =
                foodItem.getPrice() * cartItemDto.getQuantity();

        cartItem.setTotal(total);

        
        CartItem savedCartItem =
                cartItemRepository.save(cartItem);

        
        CartItemDto dto = new CartItemDto();

        dto.setCartId(savedCartItem.getCart().getId());
        dto.setFoodItemId(savedCartItem.getFooditem().getId());
        dto.setQuantity(savedCartItem.getQuantity());
        dto.setPrice(savedCartItem.getPrice());
        dto.setTotal(savedCartItem.getTotal());

        return dto;
    }
}