package com.library.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**暂时没有用到
 *
// */
public class webUtils {
    public static Object copyParamToBean(Map map, Object bean){
        try {
            //简化获取请求参数的方法
            BeanUtils.populate(bean,map);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
        return bean;
    }
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
