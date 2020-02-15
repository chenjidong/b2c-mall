package com.ppepper.account.service;

import com.ppepper.account.domain.AccountDO;
import com.ppepper.account.domain.UserDO;
import com.ppepper.account.mapper.AccountMapper;
import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.enums.AccountLoginType;
import com.ppepper.common.enums.AccountStatusType;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.redis.CacheComponent;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.common.utils.GeneratorUtil;
import com.ppepper.common.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private CacheComponent cacheComponent;

    public static final String CACHE_ACCOUNT_CODE_PREFIX = "CACHE_ACCOUNT_CODE_";

    @Override
    public AjaxResult getByPhone(String phone) {
        AccountDO accountDO = new AccountDO();
        accountDO.setPhone(phone);

        AccountDTO accountDTO = copyProperties(accountMapper.selectOne(accountDO), AccountDTO.class);
        return toAjax(accountDTO);
    }

    @Override
    public AjaxResult getByUsername(String username) {
        AccountDO accountDO = new AccountDO();
        accountDO.setUsername(username);

        AccountDTO accountDTO = copyProperties(accountMapper.selectOne(accountDO), AccountDTO.class);
        return toAjax(accountDTO);
    }

    @Override
    public AjaxResult get(Long id) {
        return toAjax(copyProperties(accountMapper.selectById(id), AccountDTO.class));
    }

    @Override
    public AjaxResult sendCode(String phone) {
        AccountDO accountDO = new AccountDO();
        accountDO.setPhone(phone);

        accountDO = accountMapper.selectOne(accountDO);
        if (accountDO != null)
            return error("号码已存在");

        String code = cacheComponent.getRaw(CACHE_ACCOUNT_CODE_PREFIX + phone);
        if (StringUtils.isNotEmpty(code))
            return success("已发送");
        code = GeneratorUtil.genSixVerifyCode();

        cacheComponent.putRaw(CACHE_ACCOUNT_CODE_PREFIX + phone, code);
        return success("发送成功", code);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public AjaxResult create(String phone, String password, String code) {
        String oldCode = cacheComponent.getRaw(CACHE_ACCOUNT_CODE_PREFIX + phone);
        if (!StringUtils.equals(oldCode, code))
            return error("验证码不正确");
        AccountDO old = new AccountDO();
        old.setPhone(phone);

        old = accountMapper.selectOne(old);
        if (old != null)
            return error("号码已存在");
        Object point = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        try {
            AccountDO accountDO = new AccountDO();
            accountDO.setUsername("b2c_" + System.currentTimeMillis());
            accountDO.setNickname("用户" + System.currentTimeMillis());
            accountDO.setPhone(phone);
            accountDO.setPassword(MD5Util.md5(password, accountDO.getUsername()));
            accountDO.setStatus(AccountStatusType.ENABLE.getCode());
            accountDO.setLoginType(AccountLoginType.USER.getCode());
            accountDO.setGmtCreate(new Date());
            accountDO.setGmtUpdate(new Date());

            int count = accountMapper.insert(accountDO);
            if (count <= 0)
                throw new RuntimeException();

            cacheComponent.del(CACHE_ACCOUNT_CODE_PREFIX + phone);
            return toAjax(count);
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(point);
        }
        return error("注册失败");
    }
}
