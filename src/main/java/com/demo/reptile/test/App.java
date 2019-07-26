package com.demo.reptile.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * App:测试访问CSDN的主要工具
 *
 * @author zhangxiaoxiang
 * @date 2019/5/25
 */
public class App {
    private static Runnable runnableDemo;


    //Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0
    public static void manypage(String uslString) {
        // 创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httpget实例
        HttpGet httpGet = new HttpGet(uslString);
        // httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64;
        // rv:50.0) Gecko/20100101 Firefox/50.0"); // 设置请求头消息User-Agent
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 执行http get请求 // 获取返回实体
        HttpEntity entity = response.getEntity();
        try {
            // System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8"));
            // System.out.println("刷新网页内容" + uslString);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } // 获取网页内容
        try {
            // response关闭
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // httpClient关闭
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Runnable RunnableDemo(String urlString) {
        // 单位: 秒(免得搞混淆)
        final long timeInterval = 65 * 1000;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    manypage(urlString);

                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        };
        return runnable;
    }

    public static Runnable countRunnable() {

        // 单位: 毫秒 // 爬取时钟
        final long timeInterval = 60 * 1000;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Integer accessNumber = CSDN.accessNumber();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println(sdf.format(new Date()) + "   数量:  " + accessNumber);
                    try {
                        Thread.sleep(timeInterval);
                        System.out.println("\n\n该时间段的水军数量:=========================== > " + (CSDN.accessNumber() - accessNumber)+"\n\n");
                        ProUtil.savePro(String.valueOf(CSDN.accessNumber()), String.valueOf((CSDN.accessNumber() - accessNumber)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        return runnable;
    }

    public static Integer accessNumber() {
        Document doc;
        Integer number = null;
        try {
            // 爬取的网站
            doc = Jsoup.connect("https://blog.csdn.net/wozniakzhang/article/list/1?").get();
            // HTML格式转文本格式(就是不带标签,只获取内容)
            String text = doc.text();
            // 这是获取到所有div里面的h2标签的元素集合,不同网站这里解析方式不一样而已
            Elements elements = doc.select("div.grade-box.clearfix > dl:nth-child(2) > dd");
            String attr = elements.attr("title");
            number = Integer.valueOf(attr);
            // 获取元素属性的值
             System.out.println("\nCSDN访问量: " + number);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return number;
    }

    public static void main(String[] args) throws Exception {
//		List<Runnable> list = new ArrayList<>();
//		List<String> urlList = new ArrayList<>();
//		List urList1 = CSDN.getUrList(1);
//		//System.out.println(urList1);
//		List urList2 = CSDN.getUrList(2);
//		urlList.addAll(urList1);
//		urlList.addAll(urList2);// 合并两个集合
//		// 开启爬取第一页的线程(混合会下标越界)
//		for (int i = 0; i < urList1.size(); i++) {
//			list.add(RunnableDemo((String) urList1.get(i)));
//		}
//		// 开启爬取第二页的线程(混合会下标越界)
//		for (int i = 0; i < urList2.size(); i++) {
//			list.add(RunnableDemo((String) urList2.get(i)));
//
//		}
//
//		Thread count = new Thread(new App().countRunnable());// 统计数量的线程
//		Thread thread = null;
//		for (int i = 0; i < list.size(); i++) {
//			thread = new Thread(list.get(i));
//			thread.start();
//
//		}
//
//		count.start();// 顺序不一定


    }
}
