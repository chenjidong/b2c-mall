package com.ppepper.sso.component;

import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        parseToken(httpServletRequest);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void parseToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(JwtTokenUtils.HEADER);//获取token
        if (!StringUtils.isEmpty(token)) {//判断token是否为空

            JwtTokenUtils.JwtSubjectBean bean = JwtTokenUtils.getSubjectByToken(token);

            //判断Security的用户认证信息
            if (bean != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(bean.getUsername());

                //把前端传递的Token信息与当前的Security的用户信息进行校验
                if (userDetails != null && JwtTokenUtils.validateToken(token, userDetails.getUsername())) {

                    // 将用户信息存入 authentication，方便后续校验
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                    // 验证正常,生成authentication
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        }
    }
}
