package com.system.controller;

import com.system.entity.User;
import com.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(){
        User user=userService.select(1);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",user);
        return new ModelAndView("index");
    }
}
