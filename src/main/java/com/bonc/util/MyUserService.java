package com.bonc.util;

import com.bonc.pojo.User;
import com.bonc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 21:44 2018/8/19
 * @Modified By:
 */
@Component
public class MyUserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
        User user = userService.getUserByName(s);
        if (user == null){
            throw new UsernameNotFoundException("没有"+s+"用户");
        }
        return new org.springframework.security.core.userdetails.
                User(user.getUsername(),passwordEncoder.encode(user.getPassword()),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
