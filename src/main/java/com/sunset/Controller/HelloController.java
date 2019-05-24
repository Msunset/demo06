package com.sunset.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;

@Controller

public class HelloController {
    @RequestMapping("/hello")
    public String hello(Map<String,Object> maps ,Model model){
        maps.put("name","<h1>张三</h1>");
        maps.put("name2","<h1>张三</h1>");
        maps.put("age","18");
        maps.put("ages", Arrays.asList("<1","2","3"));

        return "hello";
    }
}
