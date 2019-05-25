package com.sunset.Controller;

import com.sunset.dao.DepartmentDao;
import com.sunset.dao.EmployeeDao;
import com.sunset.entities.Department;
import com.sunset.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @RequestMapping("/addUser")
    public String addUser(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);

        return "addUser";
    }

    @RequestMapping("/main")
    public String main() {
        return "dashboard";
    }


    @RequestMapping("/list")
    public String list(Model model) {
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("EmpLists", all);
        return "list";

    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && username.equals("123") && password.equals("123")) {
            //登录成功,向session中存登录信息
            session.setAttribute("LoginUsername", username);
            return "redirect:/main.html";

        } else {
            map.put("Msg", "用户名或密码不正确");
            return "redirect:/login";
        }

    }
}
