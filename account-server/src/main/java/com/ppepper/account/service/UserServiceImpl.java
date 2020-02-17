package com.ppepper.account.service;

import com.ppepper.account.domain.UserDO;
import com.ppepper.account.mapper.UserMapper;
import com.ppepper.common.dto.UserDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
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
    public AjaxResult get(Long accountId) {
        UserDO userDO = new UserDO();
        userDO.setAccountId(accountId);
        return toAjax(copyProperties(userMapper.selectOne(userDO), UserDTO.class));
    }
}
