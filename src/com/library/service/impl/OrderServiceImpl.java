package com.library.service.impl;

import com.library.dao.BookDao;
import com.library.dao.OrderDao;
import com.library.dao.OrderItemDao;
import com.library.dao.impl.BookDaoImpl;
import com.library.dao.impl.OrderDaoImpl;
import com.library.dao.impl.OrderItemDaoImpl;
import com.library.pojo.*;
import com.library.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();


    @Override
    public String createOrder(Cart cart, Integer userId) {
        //先保存订单，然后保存订单项

        //必须保证订单号是唯一的，所以需要用到时间戳
        String orderId =System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);
        //遍历购物车中的每一个商品，转换成订单项保存到数据库中
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            //首先获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            //转换为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库中
            orderItemDao.saveOrderItem(orderItem);

            //修改销量和库存
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);

        }
        //清空购物车！
        cart.clear();
        return orderId;
    }

    @Override
    public List showAllOrders() {
        return null;
    }
}
