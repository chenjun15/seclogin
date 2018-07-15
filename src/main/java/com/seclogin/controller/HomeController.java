package com.seclogin.controller;

import com.seclogin.entity.SysUser;
import com.seclogin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@AllArgsConstructor
public class HomeController {

//    private final UserService userService;
    @Autowired
    SysUserService sysUserService;

    @GetMapping({"/", "/index", "/home"})
    public String root(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(SysUser sysUser){
        // 此处省略校验逻辑
        if (sysUserService.save(sysUser) != null)
            return "redirect:register?success";
        return "redirect:register?error";
    }

}
