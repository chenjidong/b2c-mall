package com.ppepper.sso.service;

import com.ppepper.common.dto.AccountDTO;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
public interface AccountService {

  public AccountDTO getByPhone(String phone);
}
