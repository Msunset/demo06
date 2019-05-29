package com.sunset.Controller;

import com.sunset.dao.DepartmentDao;
import com.sunset.dao.EmployeeDao;
import com.sunset.entities.Department;
import com.sunset.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/del/{id}")
    public String delPage(@PathVariable("id") Integer id){
        System.out.println(id);
        employeeDao.delete(id);
        return "redirect:/user/list";
    }

    //修改的提交
    @PutMapping("/addInfo")
    public String editSub(Employee employee){
        System.out.println("提交的信息："+employee);
        employeeDao.save(employee);
        return "redirect:/user/list";
    }

    //跳转到编辑页面
    @GetMapping("/editPage/{id}")
    public String editPage(@PathVariable("id")Integer id,Model model){
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        model.addAttribute("emp",employee);
        return "addUser";
    }
    //添加用户信息
    @PostMapping("/addInfo")
    public String addInfo(Employee employee){
        System.out.println("提交的信息："+employee);
        employeeDao.save(employee);
        return "redirect:/user/list";
    }

    //跳转到添加用户页面
    @RequestMapping("/addUser")
    public String addUser(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "addUser";
    }
    //跳转到首页
    @RequestMapping("/main")
    public String main() {
        return "dashboard";
    }

    //跳转到用户列表
    @RequestMapping("/list")
    public String list(Model model) {
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("EmpLists", all);
        return "list";

    }
    //登录的验证和页面的跳转
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
