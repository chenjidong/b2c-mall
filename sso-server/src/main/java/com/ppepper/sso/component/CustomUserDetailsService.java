package com.ppepper.sso.component;

import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.feign.AccountFeignService;
import com.ppepper.common.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

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
        String phone = SecurityUtils.getRealUsername(s); //加密后的username 需要解密

        // TODO: 2020-02-09 后续需处理 安全问题
        AccountDTO accountDTO = accountFeignService.getByUsername(phone);
        if (accountDTO == null)
            return null;

        List<GrantedAuthority> grantedAuth = AuthorityUtils.createAuthorityList(ROLE_USER);
        String username = SecurityUtils.getUsername(accountDTO.getPhone(), ROLE_USER);
        return new User(username, accountDTO.getPassword(), grantedAuth);
    }
}
