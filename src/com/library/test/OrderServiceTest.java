package com.library.test;

import com.library.pojo.Cart;
import com.library.pojo.CartItem;
import com.library.service.OrderService;
import com.library.service.impl.OrderServiceImpl;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class OrderServiceTest {

    @Test
    public void testCreateOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"javaWeb",5,new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"javaWeb",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"javaWeb1",1,new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号是:" + orderService.createOrder(cart, 1));
    }
}