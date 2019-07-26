package com.demo.reptile.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
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
@Slf4j
public class App {
    private static Runnable runnableDemo;

    /**
     * 根据地址模拟访问
     * @param uslString
     */
    public static void manypage(String uslString) {

        // 创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httpget实例
        HttpGet httpGet = new HttpGet(uslString);
        // 设置请求头消息User-Agent
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            // 执行http get请求 // 获取返回实体
            HttpEntity entity = response.getEntity();
            // response关闭
            response.close();
            // httpClient关闭
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟浏览器访问线程
     * @param urlString
     * @return
     */
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

    /**
     * 查询博客访问情况的定时线程
     * @return
     */
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
                        log.info("该时间段的水军数量:===> " + (CSDN.accessNumber() - accessNumber) );
                        ProUtil.savePro(String.valueOf(CSDN.accessNumber()), String.valueOf((CSDN.accessNumber() - accessNumber)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        return runnable;
    }

    /**
     * 获取博客访问数量
     * @return
     */
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
           log.info("博客目前访问量: " + number);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return number;
    }

}
