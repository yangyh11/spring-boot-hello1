package com.example.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @description: 测试实体类
 * @author: yangyh
 * @create: 2019-02-25 13:21
 **/
@Entity
public class Demo {

    @Id@GeneratedValue
    private long id;//主键

    private String name;//测试名称

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
