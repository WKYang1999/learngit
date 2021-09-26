package com.example.demo.controller;

import com.example.demo.domain.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * http://localhost:8080/role/queryById?id=1
     * @param id
     * @return Role
     */
    @GetMapping("/queryById")
    public Role queryById(Long id){
        Role role = roleService.queryById(id);
        System.out.println("queryById user => " + role.showRoleAndUserSet());
        return role;
    }

    @GetMapping("list")
    public String list(Model model){
        List<Role> roles = roleService.getRoleList();
        model.addAttribute("roles",roles);
        return "role/list";
    }

    @GetMapping("toEdit")
    public String toEdit(Model model,Long id){
        Role role = roleService.queryById(id);
        model.addAttribute("role",role);
        return "role/edit";
    }

    @PostMapping("edit")
    public String edit(Role role){
        role.setUserSet(roleService.queryById(role.getRoleId()).getUserSet());
        roleService.edit(role);
        return "redirect:list";
    }

    @GetMapping("toAdd")
    public String toAdd(){
        return "role/add";
    }

    @PostMapping("add")
    public String add(Role role){
        roleService.add(role);
        return "redirect:list";
    }

    @GetMapping("delete")
    public String delete(Long id){
        roleService.deleteById(id);
        return "redirect:list";
    }
}
