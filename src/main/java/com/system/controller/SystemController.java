package com.system.controller;

import com.system.entity.User;
import com.system.service.Impl.UserServiceImpl;
import com.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class SystemController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request , HttpServletResponse response,ModelMap modelMap){
        User user=userService.select(1);
        modelMap.addAttribute("name",user.getName());
        modelMap.addAttribute("password",user.getPassword());
        return new ModelAndView("index",modelMap);
    }
}
