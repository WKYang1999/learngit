package com.example.demo.controller;

import com.example.demo.domain.UserTB;
import com.example.demo.listener.MyListener;
import com.example.demo.service.UserTBService;
import com.example.demo.utils.ConstUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserTBService userTBService;

    private static int online = 0;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${application.message:Hello World}")
    private String message;

    @GetMapping("/asd/{name}")
    public String welcome(@PathVariable String name, Map<String ,Object> model,HttpSession session){
        session.setAttribute("name",name);
        model.put("time",new Date());
        model.put("message",this.message);
        return "redirect:/index";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping("login")
    public String login(UserTB userTB,HttpSession session){
        UserTB user = userTBService.findByNameAndPassword(userTB.getName(),userTB.getPassword());
        if(user != null){
            online ++;
            session.setAttribute(ConstUtil.SESSION_KEY,user);
            return "index";
        }
        return "login";
    }

    @RequestMapping("invalidate")
    public String invalidate(HttpSession session){
        if(session != null){
            online --;
            session.invalidate();
        }
        return "index";
    }

    @RequestMapping("index")
    public Object index(){
        return "index";
    }

    @RequestMapping("/online")
    @ResponseBody
    public Object online(){
        return "当前在线人数：" + online + "人";
    }
}
