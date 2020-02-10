package com.ppepper.common.utils;

import com.ppepper.common.security.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

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
    private static final String secret = "secret";
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
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
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
     * @param currentUsername 用户
     * @return 是否有效
     */
    public static Boolean validateToken(String token, String currentUsername) {
        String username = getUsernameFromToken(token);
        return (username.equals(currentUsername) && !isTokenExpired(token));
    }


    /**
     * 获取不带前缀 ‘ROLE_’ 角色名称
     */
    public static String getRole(String subject) {
        String name;
        try {
            name = subject.split("\\.")[1].replace(SecurityUtils.PREFIX_ROLE, "").trim();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }

        return name;
    }

    /**
     * 获取真实username
     *
     * @param username jwt 解密后的subject
     * @return
     */
    public static String getRealUsername(String username) {
        String name;
        try {
            name = username.split("\\.")[0].trim();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
        return name;
    }

    /**
     * 获取当前请求的真实username ，第一个'.' 之前表示真实的 username
     *
     * @return null
     */
    public static String getRealUsername() {
        try {
            String token = ServletUtils.getHeaderParameter(JwtTokenUtils.HEADER);
            if (!StringUtils.isEmpty(token) && !JwtTokenUtils.isTokenExpired(token)) {
                String username = JwtTokenUtils.getUsernameFromToken(token);
                return username.split("\\.")[0].trim();
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
        return null;
    }

    /**
     * \
     * 生成  jwt subject 字符串
     *
     * @param username 真实username
     * @param roles    角色数组
     * @return '.' 表示角色  ',' 隔开
     */
    public static String generateUsername(String username, String... roles) {
        StringBuilder stringBuilder = new StringBuilder();
        if (roles.length == 0)
            stringBuilder.append(roles[0]);
        else {
            for (int i = 0; i < roles.length; i++) {
                stringBuilder.append(roles[i]);
                if (i > (roles.length - 1)) {
                    stringBuilder.append(",");
                }
            }
        }
        return username + "." + stringBuilder.toString();
    }

}
