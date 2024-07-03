package com.itheima.config;

import com.itheima.domain.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User userSesson = (User)request.getSession().getAttribute("USER_SESSION");
        //已经登录,继续执行handler
        if(userSesson != null){
            return true;
        }else {
//           轉發
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);

            //重定向
//            response.sendRedirect("/admin/login.jsp");

            return false;
        }

    }

}
