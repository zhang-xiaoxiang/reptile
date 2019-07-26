package com.demo.reptile.controller;

import com.demo.reptile.test.NetworkUtil;
import com.demo.reptile.test.ProUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;


/**
 * AppController:
 *
 * @author zhangxiaoxiang
 * @date: 2019/05/25
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class AppController {


    @Value("${demo.name}")
    private String name;

    @Value("${demo.age}")
    private String age;

    public static Integer sum = 0;
    //上一次IP
    public static String oldIp = "";

    @RequestMapping("/index")
    public String goIndex(Model model, HttpServletRequest request) {
        log.info("==========================================进入了index方法================================================");
        log.info("IP地址: "+request.getRemoteAddr());
        String ip="";
        try {
            //真实IP
            ip = NetworkUtil.getIpAddress(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("真实IP:   "+ip);
        if (!ip.equals(oldIp)) {
            sum++;
        }
        oldIp = ip;
        System.out.println("请求"+ip);
        System.out.println("请求人数"+sum);


        Map map = ProUtil.readPro();
        if (map == null) {
            map.put("VisitsNum", "总访问量初始化!");
            map.put("amount", "该时段访问数量初始化!");
        }
        model.addAttribute("VisitsNum", map.get("VisitsNum"));
        model.addAttribute("amount", map.get("amount"));
        model.addAttribute("sum", sum);
        System.out.println("实时数据:"+map);
        System.out.println("---控制层----\n");
        return "index";
    }


    @RequestMapping("/info")
    @ResponseBody
    public Object goInfo(Model model, HttpServletRequest request) {
        log.info("==========================================进入了goInfo方法================================================");
        Map map = ProUtil.readPro();
        if (map == null) {
            map.put("VisitsNum", "总访问量初始化!");
            map.put("amount", "该时段访问数量初始化!");
        }
        System.out.println(name+"============"+age);
        return map;
    }

}
