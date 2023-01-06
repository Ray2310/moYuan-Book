package com.library.pojo;

import java.math.BigDecimal;
import java.util.Date;

//订单类，所有用户订单列表
public class Order {
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    private Integer status = 0;
    private  Integer userId;
 //   private List<Order>  list=  new ArrayList<>();


    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", dateTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }

    public Order() {
    }

    public Order(String orderId, Date dateTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = dateTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
