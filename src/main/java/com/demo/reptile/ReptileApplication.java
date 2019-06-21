package com.demo.reptile;

import com.demo.reptile.test.App;
import com.demo.reptile.test.CSDN;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.demo.reptile.test.App.RunnableDemo;
/**
 * ReptileApplication:
 * @author zhangxiaoxiang
 * @date 2019/6/21
 */

@SpringBootApplication
public class ReptileApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReptileApplication.class, args);
        String port = context.getEnvironment().getProperty("server.port");
        //通过getEnvironment(),getProperty()方法获取配置属性值
        System.out.println("http://localhost:"+port+"/index");
        System.out.println("http://localhost:"+port+"/info");
        List<Runnable> list = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        List urList1 = CSDN.getUrList(1);
        //System.out.println(urList1);
        List urList2 = CSDN.getUrList(2);
        urlList.addAll(urList1);
        urlList.addAll(urList2);
        // 合并两个集合
        // 开启爬取第一页的线程(混合会下标越界)
        for (int i = 0; i < urList1.size(); i++) {
            list.add(RunnableDemo((String) urList1.get(i)));
        }
        // 开启爬取第二页的线程(混合会下标越界)
        for (int i = 0; i < urList2.size(); i++) {
            list.add(RunnableDemo((String) urList2.get(i)));

        }
        // 统计数量的线程
        Thread count = new Thread(App.countRunnable());
        Thread thread ;
        for (int i = 0; i < list.size(); i++) {
            thread = new Thread(list.get(i));
            thread.start();

        }

        count.start();// 顺序不一定
    }



}
