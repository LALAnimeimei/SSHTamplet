package com.system.servlet;

import com.system.dao.Impl.UserDaoImpl;
import com.system.dao.UserDao;
import com.system.entity.UserEntity;
import com.system.service.Impl.UserServiceImpl;
import com.system.service.UserService;
import jdk.nashorn.internal.objects.annotations.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.persistence.Query;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet
public class ReportServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    private void putResponseData(HttpServletResponse response,String contentType,String headerName,String headerValue,String Data){
        try{
            response.setCharacterEncoding("GBK");
            response.setContentType(contentType);
            response.setHeader(headerName,headerValue);
            response.getWriter().write(Data);
            response.getWriter().close();
        }catch (IOException e ){
            System.out.println("response 失败");
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException{
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String reportId=request.getParameter("reportId");
        String PageCommand=request.getParameter("PageCommand");

        Enumeration<?> enu=request.getParameterNames();
        Map<String,String> para=new HashMap<String, String>();

        while(enu.hasMoreElements()){
            String key=(String)enu.nextElement();
            String value=request.getParameter(key);
            para.put(key, URLDecoder.decode(value,"UTF-8"));
        }

        if(PageCommand!=null){
            /*
            ...
            ...
            */
            return;
        }
//        Query q=userService.query("select t.id,t.name,t.password ,b.content  from User as t,Blog as b where t.id=b.userId");
        if (reportId.equals("listdata")){
            List<Object[]> result=userService.query("select t.id,t.name,t.password ,b.content  from UserEntity as t,BlogEntity as b where t.id=b.userid").list();
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
            JSONObject json=new JSONObject();
            json.put("records",jsonArray.toString());
            json.put("total",jsonArray.size());
            putResponseData(response,"application/json","Cache-Control","no-store",json.toString());
            return;
        }
        else if(reportId.equals("saveData")){
            String data=request.getParameter("data");
            Session session=userService.getSession();

           Transaction transaction=null;
            JSONArray jsonArray=JSONArray.fromObject(data);
            for(int i=0;i<jsonArray.size();i++){
                UserEntity userEntity=new UserEntity();
                JSONObject json=JSONObject.fromObject(jsonArray.get(i));
                userEntity.setId(Integer.parseInt(json.get("id").toString()));
                userEntity.setName(json.get("name").toString());
                userEntity.setPassword(json.get("password").toString());

            transaction=session.beginTransaction();

                try{

                    userService.save(userEntity);
                 transaction.commit();
                }catch (Exception e){
                    if(transaction!=null){
                       transaction.rollback();
                        e.printStackTrace();
                    }
                }finally {
                    //...
                }
            }
            session.close();
        }
    }
}
