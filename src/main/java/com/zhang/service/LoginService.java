package com.zhang.service;

import com.zhang.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

public interface LoginService {
    Admin login(String username,String password);

    Admin getAdmin(String username);

    void updateUUID(String username,String uuid);

    String getUUID(String username);

    String getPower(String username,String password);

    String pwIsRight(String username);

    int updatePW(String username,String password);
}
