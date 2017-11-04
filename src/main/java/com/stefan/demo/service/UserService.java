package com.stefan.demo.service;


import com.stefan.demo.dao.UserRepository;
import com.stefan.demo.exception.MyException;
import com.stefan.demo.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository ur;
    public List<user> findList(){
        return ur.findAll();
    }
    public user addUser(user user){
        return ur.save(user);
    }

    public user findByName(String name) throws Exception {
        return ur.findByName(name);

    }

    public List<user> findUserList(){
        return ur.findAll();
    }

    public void getAge(String name) throws Exception {
        user user=ur.findByName(name);
        Integer age=user.getAge();
        if (age<30){
            throw new MyException(100,"你正处于职业发展期");
        }else if (age>50){
            throw new MyException(101,"你马上就要退休了");
        }
    }
}
