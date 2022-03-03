package com.zhang.dao;

import com.zhang.pojo.Admin;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PowerMapper {

    @Select("select * from admin")
    List<Admin> getAllAdmin();

    @Update("update admin set  permissions=#{permissions} where username=#{username} and password=#{password}")
    int updatePermissions(Admin admin);
}
