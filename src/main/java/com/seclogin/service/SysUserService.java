package com.seclogin.service;

import com.seclogin.dao.SysUserRepository;
import com.seclogin.entity.SysRole;
import com.seclogin.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserService implements UserDetailsService {
    @Autowired
    SysUserRepository sysUserRepository;

//    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();    // TODO: 改成更优雅的方式

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = sysUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    public SysUser save(SysUser sysUser) {
        String username = sysUser.getUsername();
        if (sysUserRepository.findByUsername(username) != null) // 已存在
            return null;
        sysUser.setPassword((new BCryptPasswordEncoder()).encode(sysUser.getPassword()));
        List<SysRole> roles = new ArrayList<>();
        SysRole role = new SysRole();
        role.setId(2);
        role.setAuthority("ROLE_USER");
        roles.add(role);
        sysUser.setRoles(roles);    // TODO: 此处已写死设定角色为ROLE_USER。实际使用中请给用户选择
        return sysUserRepository.save(sysUser);
    }

    public SysUser findByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
