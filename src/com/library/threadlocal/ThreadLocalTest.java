package com.library.threadlocal;

import java.util.Hashtable;
import java.util.Map;

public class ThreadLocalTest {
    public static Map<String,Object> map = new Hashtable<>();
    public class Task implements Runnable{

        @Override
        public void run() {
            //在run方法中，随机生成一个变量（线程要关联），然后以当前线程名为key保存到map中

            //在run方法结束之前，以当前线程名获取出数据并打印。
        }
    }
}
