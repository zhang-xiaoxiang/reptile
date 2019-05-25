package com.demo.reptile.test;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * ProUtil:存到properties文件用于输出到网页(不要数据库)
 *
 * @author zhangxiaoxiang
 * @date: 2019/05/25
 */

public class ProUtil {
    /**
     * 读取数据
     */
    public static Map readPro() {
        Properties prop = new Properties();
        Map map = new HashMap();
        try {



            //获取跟目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }

           // System.out.println("path:" + path.getAbsolutePath());

            //如果上传目录为/static/，则可以如下获取：
            File datafile = new File(path.getAbsolutePath(), "static/");
            if (!datafile.exists()) {
                datafile.mkdirs();
            }
            //System.out.println("datafile url:" + datafile.getAbsolutePath());

            //读取属性文件a.properties
            InputStream in = new BufferedInputStream(new FileInputStream(datafile+"/a.properties"));
            ///加载属性列表
            prop.load(in);
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                map.put(key, prop.getProperty(key));
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return map;
    }

    /**
     * 写属性到b.properties文件
     *
     * @return
     */
    public static Map savePro(String VisitsNum, String amount) {
        Properties prop = new Properties();
        Map map = new HashMap();
        try {

            //获取跟目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
           // System.out.println("path:" + path.getAbsolutePath());

            //如果上传目录为/static/，则可以如下获取：
            File datafile = new File(path.getAbsolutePath(), "static/");
            if (!datafile.exists()) {
                datafile.mkdirs();
            }
            //System.out.println("datafile url:" + datafile.getAbsolutePath());


            ///true表示追加打开
            FileOutputStream oFile = new FileOutputStream(datafile+"/a.properties");
            prop.setProperty("VisitsNum", VisitsNum);
            prop.setProperty("amount", amount);
            prop.store(oFile, "The New properties file");
            oFile.close();
            map.put("VisitsNum", VisitsNum);
            map.put("amount", amount);
            System.out.println("写 :" + map);
            return map;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public static void main(String[] args) {
        //ProUtil.savePro("123","456");

        //Thread.sleep(10000);
        Map map = ProUtil.readPro();
        System.out.println(map);

    }
}

