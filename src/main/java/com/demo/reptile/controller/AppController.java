package com.demo.reptile.controller;

import com.demo.reptile.test.NetworkUtil;
import com.demo.reptile.test.ProUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


/**
 * AppController:
 *
 * @author zhangxiaoxiang
 * @date: 2019/05/25
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class AppController {
    public static Integer sum = 0;
    //上一次IP
    public static String oldIp = "";

    @RequestMapping("/index")
    public String goIndex(Model model, HttpServletRequest request) {
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


}
