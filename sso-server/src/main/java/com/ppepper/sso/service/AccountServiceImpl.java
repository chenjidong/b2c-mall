package com.ppepper.sso.service;

import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.sso.domain.AccountDO;
import com.ppepper.sso.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountDTO getByPhone(String phone) {
        AccountDO accountDO = new AccountDO();
        accountDO.setPhone(phone);
        return copyProperties(accountMapper.selectOne(accountDO), AccountDTO.class);
    }
}
