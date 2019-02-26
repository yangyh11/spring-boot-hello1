package com.example.demo.dao;

import com.example.demo.entity.Demo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description: 使用JdbcTemplate操作数据库
 * @author: yangyh
 * @create: 2019-02-25 16:40
 **/
@Repository
public class DemoDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过id获取Demo对象
     */
    public Demo getDemoById(long id){
        String sql = "select * from Demo where id = ?";
        RowMapper<Demo> rowMapper = new BeanPropertyRowMapper<>(Demo.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}
