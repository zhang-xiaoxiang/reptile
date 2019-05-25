package com.demo.reptile.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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
        Map map=new HashMap();
        try {
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream(new FileInputStream("src/datafile/a.properties"));
            ///加载属性列表
            prop.load(in);
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                map.put(key,prop.getProperty(key));
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return map;
    }

    /**
     * 写属性到b.properties文件
     * @return
     */
 public  static Map savePro(String VisitsNum,String  amount) {
        Properties prop = new Properties();
        Map map=new HashMap();
        try {
            ///true表示追加打开
            FileOutputStream oFile = new FileOutputStream("src/datafile/a.properties");
            prop.setProperty("VisitsNum", VisitsNum);
            prop.setProperty("amount", amount);
            prop.store(oFile, "The New properties file");
            oFile.close();
            map.put("VisitsNum",VisitsNum);
            map.put("amount",amount);
            System.out.println("写 :"+map);
            return map;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public static void main(String[] args)  {
        //ProUtil.savePro("123","456");

        //Thread.sleep(10000);
        Map map = ProUtil.readPro();
        System.out.println(map);

    }
}

