package com.ppepper.sso.config;

import com.ppepper.sso.component.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@Configuration
@EnableWebSecurity //启动 security 鉴权
/**
 * 开启注解拦截函数权限 @PreAuthorize("hasRole('role_user')")
 * tips: 使用时 需要 额外捕捉 异常信息
 */
@EnableGlobalMethodSecurity(prePostEnabled = true) //
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private NoAuthenticationEntryPoint noAuthenticationEntryPoint;
    @Autowired
    private NoAuthorityAccessDeniedHandler noAuthorityAccessDeniedHandler;
    @Autowired
    private JwtBeforeAuthenticationTokenFilter jwtBeforeAuthenticationTokenFilter;

    @Autowired
    private CustomUserDetailsAuthenticationProvider customUserDetailsAuthenticationProvider;

    @Autowired
    private HttpSecurityUrlMatchersService httpSecurityUrlMatchersService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用自己的前置拦截器 拦截 用户名密码登录filter
        http.addFilterBefore(jwtBeforeAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http.authorizeRequests();

        setupAuthorizes(authorizeRequests);

        // 请求进行拦截 验证 accessToken
        authorizeRequests
//                .antMatchers("/admin/**").authenticated()// 范围最广应在最下面
//                .antMatchers("/api/**").authenticated()// 范围最广应在最下面
                ///其他请求都可以访问
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(noAuthorityAccessDeniedHandler)
                //身份访问异常
                .authenticationEntryPoint(noAuthenticationEntryPoint)
                //权限访问异常
                .accessDeniedHandler(noAuthorityAccessDeniedHandler)
                //解决跨域
                .and()
                .cors()
                .and()
                // 关闭csrf防护
                .csrf()
                .disable();
    }

    private void setupAuthorizes(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {

        for (String role : httpSecurityUrlMatchersService.onLoadPermission().keySet()) {
            if (httpSecurityUrlMatchersService.type() == 0) {
                authorizeRequests.antMatchers(httpSecurityUrlMatchersService.onLoadPermission().get(role).toArray(new String[]{})).hasRole(role);
            } else if (httpSecurityUrlMatchersService.type() == 1) {
                authorizeRequests.antMatchers(httpSecurityUrlMatchersService.onLoadPermission().get(role).toArray(new String[]{})).hasAuthority(role);
            }
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customUserDetailsAuthenticationProvider);
    }

    //必须配置 手动验证需要
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
