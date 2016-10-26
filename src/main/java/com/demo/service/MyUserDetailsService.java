package com.demo.service;

import com.demo.dao.UserDao;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Pinggang Yu on 2016/10/25.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Resource
    private UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map userMap = userDao.loadUsersByUsername(username);

        if(userMap == null || userMap.size() == 0) {
            throw new UsernameNotFoundException(this.messages.getMessage("JdbcDaoImpl.notFound", new Object[]{username}, "Username {0} not found"));
        } else {

            List<String> authoritiesList = userDao.loadUserAuthorities((Integer) userMap.get("userId"));
            if (authoritiesList.size() == 0) {
                throw new UsernameNotFoundException(this.messages.getMessage("JdbcDaoImpl.noAuthority", new Object[]{username}, "User {0} has no GrantedAuthority"));
            }else {
                HashSet<GrantedAuthority> authenticationHashSet = new HashSet<GrantedAuthority>();
                for (String index:authoritiesList) {
                    authenticationHashSet.add(new SimpleGrantedAuthority(index));
                }
                User user = new User((String) userMap.get("username"), (String) userMap.get("password"),authenticationHashSet);
                System.out.println(user.toString());
                return new  User((String) userMap.get("username"), (String) userMap.get("password"),(Boolean) userMap.get("enable"),true, true, true,authenticationHashSet);
            }

        }

    }
}
