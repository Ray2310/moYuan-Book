package com.library.test;

import com.library.dao.OrderDao;
import com.library.dao.impl.OrderDaoImpl;
import com.library.pojo.Order;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.testng.Assert.*;

public class OrderDaoTest {

    @Test
    public void testSaveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        //userId必须是已存在的，不然就会报错
        orderDao.saveOrder(new Order("123321123",new Date(),new BigDecimal(100),0,1));

    }
}