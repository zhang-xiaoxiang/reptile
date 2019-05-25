package com.demo.reptile.test;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * ProUtil:存到properties文件用于输出到网页(模拟数据库)
 *
 * @author zhangxiaoxiang
 * @date: 2019/05/25
 */

public class ProUtil {

    /**
     * 获取根路径
     * #设置静态资源路径，多个以逗号分隔
     * spring.resources.static-locations=classpath:static/,file:static/
     * @param filePath 就是配置文件配置的路径,如file:static/,那么就传static/就行了
     * @return 返回根路径,之后你再把文件名拼接类型加上去就行了
     * @throws FileNotFoundException
     */

   public static File getFilePath(String filePath) throws FileNotFoundException {

       //获取跟目录
       File path = new File(ResourceUtils.getURL("classpath:").getPath());
       if (!path.exists()) {
           path = new File("");
       }

       /**
        * 如果上传目录为/static/，则可以如下获取：
        */
       File datafile = new File(path.getAbsolutePath(), filePath);
       if (!datafile.exists()) {
           datafile.mkdirs();
       }
       return datafile;

   }




    /**
     * 读取数据
     */
    public static Map readPro() {
        Properties prop = new Properties();
        Map map = new HashMap();
        try {
            File filePath = getFilePath("static/");
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream(new FileInputStream(filePath+"/a.properties"));
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
            File filePath = getFilePath("static/");
            ///true表示追加打开,这里不需要
            FileOutputStream oFile = new FileOutputStream(filePath+"/a.properties");
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

