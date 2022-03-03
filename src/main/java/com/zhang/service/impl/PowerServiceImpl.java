package com.zhang.service.impl;

import com.zhang.dao.PowerMapper;
import com.zhang.pojo.Admin;
import com.zhang.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerMapper powerMapper;

    @Override
    public List<Admin> getAllAdmin() {
        return powerMapper.getAllAdmin();
    }

    @Override
    public int updatePermissions(Admin admin) {
        return powerMapper.updatePermissions(admin);
    }
}
