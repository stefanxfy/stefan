package com.stefan.demo.controller;

import com.stefan.demo.pojo.Result;
import com.stefan.demo.pojo.user;
import com.stefan.demo.service.UserService;
import com.stefan.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService us;
    @GetMapping("/save")
    public Result<user> save(@Valid   user user,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(us.addUser(user));
    }
    @GetMapping("/getUsers")
    public List<user> getUsers(@RequestParam("name") String name) throws Exception {
        return us.findByName(name);
    }
    @GetMapping("/get")
    public List<user> get(){
        return us.findUserList();
    }



}
