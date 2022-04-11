package com.bookmails.utils;

import com.bookmails.pojo.user;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class webutiles {
    //快捷注入参数
    public static <T> T copyParamToBean(HttpServletRequest req,T bean )
    {
//        user u = new user();
//        System.out.println("注入之前："+bean);
//        System.out.println("注入之后"+bean);
        try {
            BeanUtils.populate(bean,req.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
          return bean;
    }
}
