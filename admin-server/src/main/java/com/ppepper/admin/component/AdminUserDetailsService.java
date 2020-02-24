package com.ppepper.admin.component;

import com.ppepper.admin.service.AdminService;
import com.ppepper.common.dto.AdminDTO;
import com.ppepper.common.security.SecurityUtils;
import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-21
 * 覆盖 sso 服务中的 security AccountUserDetailsService
 */
@Component
@Primary
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminDTO adminDTO = adminService.getByUsername(username);
        if (adminDTO == null || adminDTO.getPerms() == null)
            throw new UsernameNotFoundException("用户或密码不正确");

        String username1 = JwtTokenUtils.generateSubject(adminDTO.getUsername(), adminDTO.getId(), SecurityUtils.ROLE_ADMIN);

        List<GrantedAuthority> authorities = new ArrayList<>(adminDTO.getPerms().size());

        for (String permission : adminDTO.getPerms()) {
            if (!"*".equals(permission))
                authorities.add(new SimpleGrantedAuthority(permission));
        }

        return new User(username1, adminDTO.getPassword(), authorities);

    }
}
