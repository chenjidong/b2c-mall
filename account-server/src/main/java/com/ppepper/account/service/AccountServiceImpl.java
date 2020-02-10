package com.ppepper.account.service;

import com.ppepper.account.domain.AccountDO;
import com.ppepper.account.mapper.AccountMapper;
import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
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
    public AjaxResult getByPhone(String phone) {
        AccountDO accountDO = new AccountDO();
        accountDO.setPhone(phone);

        AccountDTO accountDTO = copyProperties(accountMapper.selectOne(accountDO), AccountDTO.class);
        return success(accountDTO);
    }
}
