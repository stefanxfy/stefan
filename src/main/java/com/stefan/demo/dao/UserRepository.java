package com.stefan.demo.dao;

import com.stefan.demo.pojo.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<user,Long> {
    public  List<user> findByName(String name)throws Exception;
}
