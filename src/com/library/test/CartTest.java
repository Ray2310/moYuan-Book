package com.library.test;

import com.library.pojo.Cart;
import com.library.pojo.CartItem;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class CartTest {

    @Test
    public void testAddItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"javaWeb",5,new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"javaWeb",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"javaWeb1",1,new BigDecimal(100)));
        System.out.println(cart);
    }


    @Test
    public void testDeleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"javaWeb",5,new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"javaWeb",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"javaWeb1",1,new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart);

    }

    @Test
    public void testClear() {
        Cart cart = new Cart();
        cart.clear();
    }

    @Test
    public void testUpdateCount() {
    }
}