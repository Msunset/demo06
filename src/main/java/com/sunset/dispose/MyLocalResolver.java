package com.sunset.dispose;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        //没有带来信息就用默认系统自带的locale，有的话就在下面给locale赋值我们带来的信息
        Locale locale =Locale.getDefault();
        if (!StringUtils.isEmpty(l)){
            String[] s = l.split("_");
//            locale对象两个参数分别对应language和country
            locale = new Locale(s[0], s[1]);

        }

//把我们组装好的信息return出去
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
