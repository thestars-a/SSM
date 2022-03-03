package com.zhang.service;

import com.zhang.pojo.Admin;

import java.util.List;

public interface PowerService {
    List<Admin> getAllAdmin();

    int updatePermissions(Admin admin);
}
