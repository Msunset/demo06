package com.sunset.Configurer;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUsername = request.getSession().getAttribute("LoginUsername");
        if (loginUsername != null){
            //返回登录成功的页面

            return true;
            //修改了文件
            //有一次的修改

        }else {
            //登录失败转发到登录页面
            request.setAttribute("Msg","没有权限访问，请先登录！");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
