package com.stefan.demo.service;


import com.stefan.demo.dao.UserRepository;
import com.stefan.demo.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<user> findByName(String name) throws Exception {
        return ur.findByName(name);

    }

    public List<user> findUserList(){
        return ur.findAll();
    }

}
