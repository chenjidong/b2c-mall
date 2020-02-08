package com.ppepper.sso.service;

import com.ppepper.common.dto.UserDTO;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
public interface UserService {

    public String sendVerifyCode(String phone);

    public String register(
            String phone,
            String password,
            String verifyCode,
            String ip);

    public String bindPhone(
            String phone,
            String password,
            String verifyCode,
            Long userId);

    public String resetPassword(
            String phone,
            String password,
            String verifyCode);

    public UserDTO login(
            String phone,
            String password,
            Integer loginType,
            String raw,
            String ip);

    public String logout(
            String accessToken,
            Long userId);

    public UserDTO thirdPartLogin(
            Integer loginType,
            String ip,
            String raw);

    public String syncUserInfo(
            String nickName,
            String nickname,
            String avatarUrl,
            Integer gender,
            Long birthday,
            String accessToken,
            Long userId);

    public Object getH5Sign(String url);


    public UserDTO get(Long id);
}
