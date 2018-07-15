package com.seclogin.service;

import com.seclogin.dao.SysUserRepository;
import com.seclogin.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {
    @Autowired
    SysUserRepository sysUserRepository;

    public SysUser save(SysUser sysUser) {
        String username = sysUser.getUsername();
        if (sysUserRepository.findByUsername(username) != null) // 已存在
            return null;
        encryptPassword(sysUser);
//        userEntity.setRoles(RoleConstant.ROLE_USER);
        return sysUserRepository.save(sysUser);
    }

    public SysUser findByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }

    /**
     * 加密密码
     */
    private void encryptPassword(SysUser userEntity) {
        String password = userEntity.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        userEntity.setPassword(password);
    }
}
