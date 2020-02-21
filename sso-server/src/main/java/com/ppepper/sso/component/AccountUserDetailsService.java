package com.ppepper.sso.component;

import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.enums.AccountLoginType;
import com.ppepper.common.feign.AccountSysFeignService;
import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.ppepper.common.security.SecurityUtils.ROLE_SHOP;
import static com.ppepper.common.security.SecurityUtils.ROLE_USER;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 * tips：需要拆分时  使用 feign 获取即可
 */
@Component
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountSysFeignService accountSysFeignService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AccountDTO accountDTO = accountSysFeignService.getByPhone(s);
        if (accountDTO == null) {
            accountDTO = accountSysFeignService.getByUsername(s);
            if (accountDTO == null)
                throw new UsernameNotFoundException("账户不存在！");
        }
        String role = accountDTO.getLoginType() == AccountLoginType.SHOP.getCode() ? ROLE_SHOP : ROLE_USER;
        String name = accountDTO.getUsername();
        if (name == null)
            name = accountDTO.getPhone();

        String username = JwtTokenUtils.generateSubject(name, accountDTO.getId(), role);
        return new User(username, accountDTO.getPassword(), AuthorityUtils.createAuthorityList(role));
    }
}
