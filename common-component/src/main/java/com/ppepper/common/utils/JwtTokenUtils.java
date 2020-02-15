package com.ppepper.common.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 * jwt 配合 security 封装 token 信息
 * tips: username 表示 security 中的username 非逻辑
 */
public class JwtTokenUtils {
    private static final String secret = "qwertyuiopasdfghjklzxcvbnm1234567890";
    private static final Long expiration = 1000 * 60 * 60 * 24 * 7L;//7天
    public static final String HEADER = "Authorization";

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成令牌
     *
     * @param subject 声明数据
     * @param millis  毫秒值
     * @return
     */
    public static String generateToken(String subject, Long millis) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT, subject);
        claims.put(Claims.ISSUED_AT, new Date());

        Date expirationDate = new Date(System.currentTimeMillis() + millis);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @param subject 用户信息
     * @return 令牌
     */
    public static String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT, subject);
        claims.put(Claims.ISSUED_AT, new Date());
        return generateToken(claims);
    }

    /**
     * 从令牌中获取 subject json 信息
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String decodeToken(String token) {
        String subject;
        try {
            Claims claims = getClaimsFromToken(token);
            subject = claims.getSubject();
        } catch (Exception e) {
            subject = null;
        }
        return subject;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token           令牌
     * @param currentUsername 处理后的 username {@link JwtTokenUtils#generateSubject(java.lang.String, java.lang.Long, java.lang.String...)}
     * @return 是否有效
     */
    public static Boolean validateToken(String token, String currentUsername) {
        String username = decodeToken(token);
        return (currentUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * @param token 加密的token
     */
    public static JwtSubjectBean getSubjectByToken(String token) {
        try {
            return JSON.parseObject(decodeToken(token), JwtSubjectBean.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前请求的真实username JwtSubjectBean
     *
     * @return null
     */
    public static JwtSubjectBean getCurrentSubjectByToken() {
        try {
            String token = ServletUtils.getHeaderParameter(JwtTokenUtils.HEADER);
            if (!StringUtils.isEmpty(token) && !JwtTokenUtils.isTokenExpired(token)) {
                return getSubjectByToken(token);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 获取当前 请求 token 中的accountId
     *
     * @return 默认0  避免mybatis  空判断导致返回全部
     */
    public static Long getCurrentAccountIdByToken() {
        JwtSubjectBean bean = getCurrentSubjectByToken();
        if (bean != null)
            return bean.getAccountId();

        return 0L;
    }

    /**
     * 获取当前请求 token 中的 username
     *
     * @return sso 服务中 使用手机号
     */
    public static String getCurrentUsernameByToken() {
        JwtSubjectBean bean = getCurrentSubjectByToken();
        if (bean != null)
            return bean.getUsername();

        return null;
    }

    /**
     * 生成  jwt subject 字符串
     */
    public static String generateSubject(String username, Long accountId, String... roles) {
        return JSON.toJSONString(new JwtTokenUtils.JwtSubjectBean(username, accountId, roles));
    }


    /**
     * 自定义 jwt subject 内容 方便解析
     * todo 不要把密码放进去
     */
    public static class JwtSubjectBean implements Serializable {
        private String username;//真实的 username 既 手机号
        private Long accountId;
        private String[] roles;

        public JwtSubjectBean() {
        }

        public JwtSubjectBean(String username, Long accountId, String[] roles) {
            this.username = username;
            this.accountId = accountId;
            this.roles = roles;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Long getAccountId() {
            return accountId;
        }

        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }
    }
}
