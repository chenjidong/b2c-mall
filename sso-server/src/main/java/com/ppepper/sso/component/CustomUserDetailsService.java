package com.ppepper.sso.component;

import com.ppepper.sso.domain.AccountDO;
import com.ppepper.sso.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 * tips：需要拆分时  使用 feign 获取即可
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AccountDO accountDO = new AccountDO();
        accountDO.setPhone(s);
        accountDO = accountMapper.selectOne(accountDO);
        if (accountDO == null)
            return null;

        List<GrantedAuthority> grantedAuth = AuthorityUtils.createAuthorityList(ROLE_USER);

        return new User(accountDO.getPhone(), accountDO.getPassword(), grantedAuth);
    }
}
