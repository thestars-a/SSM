package com.zhang.service.impl;

import com.zhang.dao.LoginMapper;
import com.zhang.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements com.zhang.service.LoginService {

    @Autowired
    private LoginMapper loginMapper;


    @Override
    public Admin login(String username,String password) {
        return loginMapper.login(username, password);
    }

    @Override
    public Admin getAdmin(String username) {
        return loginMapper.getAdmin(username);
    }

    @Override
    public void updateUUID(String username,String uuid) {
        loginMapper.updateUUID(username,uuid);
    }

    @Override
    public String getUUID(String username) {
        return loginMapper.getUUID(username);
    }

    @Override
    public String getPower(String username, String password) {
        return loginMapper.getPower(username, password);
    }

    @Override
    public String pwIsRight(String username) {
        return loginMapper.pwIsRight(username);
    }

    @Override
    public int updatePW(String username, String password) {
        return loginMapper.updatePW(username, password);
    }
}
