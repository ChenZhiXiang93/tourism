package com.bonc.service.impl;

import com.bonc.mapper.UserMapper;
import com.bonc.pojo.Tourism;
import com.bonc.pojo.User;
import com.bonc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:47 2018/8/24
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

}
