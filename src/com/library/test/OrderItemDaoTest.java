package com.library.test;

import com.library.dao.OrderItemDao;
import com.library.dao.impl.OrderItemDaoImpl;
import com.library.pojo.OrderItem;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void testSaveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"123321123"));
    }
}