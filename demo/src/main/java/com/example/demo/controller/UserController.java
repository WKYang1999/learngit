package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Resource
    UserService userService;


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users = userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/add";
    }

    @RequestMapping("/add")
    public String add(User user) {
        userService.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        Optional<User> user=userService.findUserById(id);
        model.addAttribute("user", user.get());
        return "user/edit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/list";
    }


    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.deleteById(id);
        return "redirect:/list";
    }

    @RequestMapping("/find")
    public String find() {
        return "user/find";
    }

    @RequestMapping("/findByName")
    public String listByName(User user,Model model){
        List<User> users = userService.findByUserName(user.getUserName());
        model.addAttribute("users",users);
        return "user/listByName";
    }

    @RequestMapping("/findNA")
    public String findNA() {
        return "user/findByNameOrAge";
    }

    @RequestMapping("/findByNameOrAge")
    public String listByNA(User user,Model model){
        List<User> users = userService.findByUserNameOrAge(user.getUserName(),user.getAge());
        model.addAttribute("users",users);
        return "user/listByName";
    }

    @RequestMapping("/findNAA")
    public String findNAA() {
        return "user/findByNameAndAge";
    }

    @RequestMapping("/findByNameAndAge")
    public String listByNAA(User user,Model model){
        List<User> users = userService.findByUserNameAndAge(user.getUserName(),user.getAge());
        model.addAttribute("users",users);
        return "user/listByName";
    }
}
