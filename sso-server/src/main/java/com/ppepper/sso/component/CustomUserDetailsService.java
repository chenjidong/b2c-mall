package com.ppepper.sso.component;

import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.enums.AccountLoginType;
import com.ppepper.common.feign.AccountFeignService;
import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ppepper.common.security.SecurityUtils.ROLE_ADMIN;
import static com.ppepper.common.security.SecurityUtils.ROLE_USER;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 * tips：需要拆分时  使用 feign 获取即可
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountFeignService accountFeignService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AccountDTO accountDTO = accountFeignService.getByPhone(s);
        if (accountDTO == null) {
            accountDTO = accountFeignService.getByUsername(s);
            if (accountDTO == null)
                return null;
        }
        String role = accountDTO.getLoginType() == AccountLoginType.ADMIN.getCode() ? ROLE_ADMIN : ROLE_USER;
        String username = JwtTokenUtils.generateSubject(accountDTO.getUsername(), accountDTO.getId(), role);
        return new User(username, accountDTO.getPassword(), AuthorityUtils.createAuthorityList(role));
    }
}
