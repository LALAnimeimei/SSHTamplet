package com.system.service.Impl;


import com.system.dao.UserDao;
import com.system.entity.UserEntity;
import com.system.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;

    @Transactional
    public void saveDate(HttpServletRequest request) {
        String data=request.getParameter("data");
        Session session=getSession();

        //开启事务
        Transaction transaction=session.beginTransaction();
        JSONArray jsonArray=JSONArray.fromObject(data);

        //删除数据
//      手动创建对象，然后删除
//        UserEntity duserEntity=new UserEntity();
//        duserEntity.setId(3);
//        session.delete(duserEntity);

        //先查询，然后删除

        List list=null;
        try {
            list=  session.createQuery("from UserEntity as  u where u.name='d'").getResultList();
        }catch (NoResultException var10){

        }
        UserEntity[] sd=new UserEntity[list.size()];
        //取出查出的满足条件的对象数组
        for(int j=0;j<list.size();j++){
            sd[j]=(UserEntity) list.get(j);
        }

        //对每条修改的记录放到实体里去，这里其实可以只获取修改的那一个字段，其余由实体中获取
        for(int i=0;i<jsonArray.size();i++){
            UserEntity userEntity=new UserEntity();
            JSONObject json=JSONObject.fromObject(jsonArray.get(i));
            userEntity.setId(Integer.parseInt(json.get("id").toString()));
            userEntity.setName(json.get("name").toString());
            userEntity.setPassword(json.get("password").toString());

            try{
                //逐条删除
                if(list.size()>0)
                    for(int j=0;j<list.size();j++){
                        session.delete(sd[j]);
                    }

                //这里之前commit一直没有生效又不报错的原因是数据库中的主键设置了自增
                //更新实体
                session.update(userEntity);

                //提交事务
                transaction.commit();
                sd=null;
            }catch (Exception e){
                //事务失败则回滚
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

    public Session getSession(){return  userDao.getSession();}
    public UserEntity select(int id){
        return userDao.select(id);
    }
    public Query query(String s){
        return userDao.query(s);
    }
    @Transactional
    public Serializable save(Object s){
        return userDao.save(s);
    }
}
