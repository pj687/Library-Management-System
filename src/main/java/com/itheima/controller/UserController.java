package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.pojo.UserDto;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping("/login")
    public String login(UserDto userDto, HttpServletRequest request, Model model){
        User user=new User();
        user.setEmail(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        User login = userService.login(user);
        if (login==null){
            model.addAttribute("msg", "账号或者密码错误！");
            return "forward:/admin/login.jsp";
        }else {
            request.getSession().setAttribute("USER_SESSION",login);
            return "redirect:/admin/main.jsp";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        //
        HttpSession session = request.getSession();
        session.invalidate();
        return "login";
    }
}
