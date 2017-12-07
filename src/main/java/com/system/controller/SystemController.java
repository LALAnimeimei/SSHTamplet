package com.system.controller;

import com.system.entity.User;
import com.system.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import net.sf.json.JSONArray;
import java.util.List;


@Controller
public class SystemController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public  ModelAndView login(HttpServletRequest request,HttpServletResponse response){
        return new ModelAndView("welcome/welcom");
    }

    @RequestMapping("/querydata")
    public ModelAndView querydata(HttpServletRequest request , HttpServletResponse response,ModelMap modelMap){
        User user=userService.select(1);
        List<Object[]> result=userService.query("select t.id,t.name,t.password ,b.content  from User as t,Blog as b where t.id=b.userId").list();
//        modelMap.addAttribute("name",user.getName());
//        modelMap.addAttribute("password",user.getPassword());
        JSONObject obj=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<result.size();i++)
        {
            Object[] x=result.get(i);
            obj.put("id",x[0].toString());
            obj.put("name",x[1].toString());
            obj.put("password",x[2].toString());
            obj.put("content",x[3].toString());
            jsonArray.add(obj);
        }
        modelMap.addAttribute("data",jsonArray.toString());
        return new ModelAndView("index",modelMap);
    }
}
