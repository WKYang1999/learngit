package com.example.demo.controller;

import com.example.demo.domain.Role;
import com.example.demo.domain.UserTB;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserTBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@RestController
@Controller
@RequestMapping("userTB")
public class UserTBController {

    @Autowired
    UserTBService userTBService;

    @Autowired
    RoleService roleService;

    /**
     * http://localhost:8080/userTB/queryById?id=1
     * @param id
     * @return UserTB
     */
    @GetMapping("/queryById")
    public UserTB queryById(Long id){
        UserTB user = userTBService.queryById(id);
        System.out.println("queryById user => " + user.showUserAndRoleSet());
        return user;
    }

    @GetMapping("list")
    public String list(Model model){
        Sort sort = Sort.by(Sort.Direction.DESC,"age");
        List<UserTB> userTBList = userTBService.getUserTBList();
        model.addAttribute("usersTB",userTBList);
        return "userTB/list";
    }

    @GetMapping("add")
    public String add(Model model){
        List<Role> roles = roleService.getRoleList();
        model.addAttribute("roles",roles);
        return "userTB/add";
    }

    @PostMapping("toAdd")
    public String toAdd(UserTB userTB){
        userTBService.save(userTB);
        return "redirect:list";
    }

    @GetMapping("edit")
    public String edit(Model model, Long id){
        UserTB user = userTBService.queryById(id);
        List<Role> roles = roleService.getRoleList();
        model.addAttribute("roles",roles);
        model.addAttribute("user",user);
        return "userTB/edit";
    }

    @PostMapping("toEdit")
    public String toEdit(UserTB userTB){
        userTBService.edit(userTB);
        return "redirect:list";
    }

    @GetMapping("delete")
    public String delete(Long id){
        userTBService.deleteById(id);
        return "redirect:list";
    }

    //跳转到查询页面(名字Or性别)
    @GetMapping("findNA")
    public String findNA(){
        return "userTB/findNA";
    }
    //执行查询并跳转到显示页面(名字Or性别)
    @PostMapping("findNA")
    public String findNA(Model model,UserTB userTB){
        List<UserTB> userTBList = userTBService.findByNameOrUserSex(userTB.getName(),userTB.getUserSex());
        model.addAttribute("usersTB",userTBList);
        return "userTB/list";
    }
    //跳转到查询页面(名字And性别)
    @GetMapping("findNAA")
    public String findNAA(){
        return "userTB/findNAA";
    }
    //执行查询并跳转到显示页面(名字And性别)
    @PostMapping("findNAA")
    public String findNAA(Model model,UserTB userTB){
        List<UserTB> userTBList = userTBService.findByNameAndUserSex(userTB.getName(),userTB.getUserSex());
        model.addAttribute("usersTB",userTBList);
        return "userTB/list";
    }
}
