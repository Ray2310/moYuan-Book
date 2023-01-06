package com.library.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //先查看购物车中是否添加过此商品，如果已经添加，那么就需要数量加1，总金额*2
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            //之前没有添加过此商品
            items.put(cartItem.getId(), cartItem);
        }else{
            //数量增加
            item.setCount(item.getCount()+1);
            //更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    /**
     * 删除商品项----不是商品减一，而是直接将商品项给删除了
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }/**
     * 清空购物车
     * @param
     */
    public void clear(){
        items.clear();
    }/**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void UpdateCount(Integer id,Integer count){
        //先查看是否有此商品，如果有那么就更新
        CartItem cartItem =items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);// 修改商品数量
            //cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }
    public Cart() {
    }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }



    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
