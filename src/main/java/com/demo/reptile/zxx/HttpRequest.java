package com.demo.reptile.zxx;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequest {


    public static void main(String[] args) {
        // 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
        System.getProperties().setProperty("http.proxyHost", "58.218.200.223");
        System.getProperties().setProperty("http.proxyPort", "30000");
        // 判断代理是否设置成功
        // 发送 GET 请求
        // System.out.println(sendGet(
        //         "http://www.baidu.com",
        //         "param1=xxx&param2=yyy"));
        // 发送 POST 请求
        sendGet("https://blog.csdn.net/wozniakzhang/article/details/93323593","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/96912106","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/96901836","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/96213281","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/95928195","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/95855296","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/95459796","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/95214254","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/94667441","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/93305382","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/92174287","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/91983174","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/91868669","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/90526668","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/90138418","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/90138119","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/89814231","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/89703065","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/89681562","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/89501701","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/86350877","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/86355935","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/89390595","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/89362018","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/89250229","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/89180807","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/89156635","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/88897181","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/88315559","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/87903327","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/87900459","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/87531390","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/87428203","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/86345087","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/87268424","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/86773134","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/86771714","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/86770651","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/86631385","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/86491809","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/86306761","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/85784029","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/85482985","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/85225331","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/85222414","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/84927175","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/84842388","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/84636578","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/84626294","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/84580083","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/84256090","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/84069547","");
        // sendGet("https://blog.csdn.net/wozniakzhang/article/details/80772314","");








































    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}