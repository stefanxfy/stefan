package com.stefan.demo.serviceTest;

import com.stefan.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
对service的测试
 */
/**
 * Created by Stefan
 * Create Date 2017-11-04/16:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService us;
    @Test
    public void findByNameTest() throws Exception {
         us.findByName("xfy");
        Assert.assertEquals(new Integer(20),us.findByName("xfy").getAge());
    }
}
