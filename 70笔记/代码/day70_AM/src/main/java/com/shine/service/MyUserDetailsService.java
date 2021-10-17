package com.shine.service;

import com.shine.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    UserDao userDao;

    /**
     *  得到用户设置详细信息的对象
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_user");

        /**
         * 使用此用户名向数据库发起查询
         *  查询得到此用户的所有信息--密码
         *  把次用户封装成对象
         *
         *  创建user对象的时候传入查询得到的密码
         */
        com.shine.entity.User u = userDao.selectByUsername(username);

        if (u!=null){
            User user = new User(username,passwordEncoder.encode(u.getPassword()),authorities);
            return user;
        }
        return null;
    }
}
