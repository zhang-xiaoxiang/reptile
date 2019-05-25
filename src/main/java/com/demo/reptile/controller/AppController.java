package com.demo.reptile.controller;

import com.demo.reptile.test.ProUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * AppController:
 *
 * @author zhangxiaoxiang
 * @date: 2019/05/25
 */
@Controller
@CrossOrigin("")
public class AppController {

    @RequestMapping("/index")
    public   String goIndex(Model model){
        System.out.println("---控制层----");
        Map map = ProUtil.readPro();
        if (map==null){
            map.put("VisitsNum","总访问量初始化!");
            map.put("amount","该时段访问数量初始化!");
        }
        model.addAttribute("VisitsNum",map.get("VisitsNum"));
        model.addAttribute("amount",map.get("amount"));
        System.out.println(map);

        return "index";
    }


}
