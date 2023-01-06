package com.library.test;
import com.library.utils.jdbcUtils;
import org.testng.annotations.Test;
public class jdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        //获取的连接最大只能是10个，前面设置过
        System.out.println(jdbcUtils.getConnection());
    }
}
