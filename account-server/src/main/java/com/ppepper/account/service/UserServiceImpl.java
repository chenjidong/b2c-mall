package com.ppepper.account.service;

import com.ppepper.common.dto.UserDTO;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.account.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String sendVerifyCode(String phone) {
        return null;
    }

    @Override
    public String register(String phone, String password, String verifyCode, String ip) {
        return null;
    }

    @Override
    public String bindPhone(String phone, String password, String verifyCode, Long userId) {
        return null;
    }

    @Override
    public String resetPassword(String phone, String password, String verifyCode) {
        return null;
    }

    @Override
    public UserDTO login(String phone, String password, Integer loginType, String raw, String ip) {
        return null;
    }

    @Override
    public String logout(String accessToken, Long userId) {
        return null;
    }

    @Override
    public UserDTO thirdPartLogin(Integer loginType, String ip, String raw) {
        return null;
    }

    @Override
    public String syncUserInfo(String nickName, String nickname, String avatarUrl, Integer gender, Long birthday, String accessToken, Long userId) {
        return null;
    }

    @Override
    public Object getH5Sign(String url) {
        return null;
    }

    @Override
    public UserDTO get(Long id) {
        return copyProperties(userMapper.selectById(id), UserDTO.class);
    }
}
