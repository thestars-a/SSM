package com.zhang.dao;

import com.zhang.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface LoginMapper {
    @Select("select username,password from admin where username = #{username} and password = #{password}")
    Admin login(@Param("username") String username,@Param("password") String password);


    @Select("select username from admin where username = #{username}")
    Admin getAdmin(@Param("username") String username);

    @Select("select permissions from admin where username = #{username} and password = #{password}")
    String getPower(@Param("username") String username,@Param("password") String password);

    @Update("update admin set `uuid`=#{uuid} where username = #{username}")
    void updateUUID(@Param("username") String username,@Param("uuid") String uuid);

    @Select("select uuid from admin where username = #{username}")
    String getUUID(@Param("username") String username);

    @Select("select password from admin where username = #{username}")
    String pwIsRight(@Param("username") String username);

    @Update("update admin set password=#{password} where username = #{username}")
    int updatePW(@Param("username") String username,@Param("password") String password);
}
