package com.example.dao;

import com.example.entity.IpInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 使用JdbcTemplate操作数据库
 * @author: yangyh
 * @create: 2019-02-25 16:40
 **/
@Repository
public class IpInfoDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过id获取Demo对象
     */
    public List<IpInfo> getIpInfo(){
        String sql = "select * from ip_info order by id";
        RowMapper<IpInfo> rowMapper = new BeanPropertyRowMapper<>(IpInfo.class);
        List<IpInfo> ipList = jdbcTemplate.query(sql, rowMapper);
        return ipList;
    }


    /**更新ip所属位置信息**/
    public void updateLocation(long id, String location) {
        String sql = "update ip_info set location = ? where id = ?";
        jdbcTemplate.update(sql, new Object[] {location, id});
    }

}
