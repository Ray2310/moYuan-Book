package com.library.service;

import com.library.pojo.Cart;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
    //public List<>
    public List showAllOrders();
}
