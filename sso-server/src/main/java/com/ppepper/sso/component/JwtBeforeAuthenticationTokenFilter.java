package com.ppepper.sso.component;

import com.ppepper.common.jwt.JwtTokenComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 * 判断JWT的token信息，进行相应的处理
 */
@Component
public class JwtBeforeAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenComponent jwtTokenComponent;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(jwtTokenComponent.getHeader());//获取token
        if (!StringUtils.isEmpty(token)) {//判断token是否为空
            String username = jwtTokenComponent.getUsernameFromToken(token);//取出token的用户信息
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {//判断Security的用户认证信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtTokenComponent.validateToken(token, userDetails.getUsername())) {//把前端传递的Token信息与当前的Security的用户信息进行校验
                    // 将用户信息存入 authentication，方便后续校验
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                    // 验证正常,生成authentication
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
