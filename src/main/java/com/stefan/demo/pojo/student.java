package com.stefan.demo.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Stefan
 * Create Date 2017-11-04/12:19
 */

@Table(name="t_user")
@Entity
@Component
@ConfigurationProperties(prefix = "student")
public class student {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String campusName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }
}
