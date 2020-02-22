package com.ppepper.account.service;

import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
public interface AccountService {

    public AjaxResult getByPhone(String phone);

    public AjaxResult getByUsername(String username);

    public AjaxResult get(Long id);

    public AjaxResult sendCode(String phone);

    public AjaxResult create(String phone, String password, String code);

    public Page<AccountDTO> list(Integer page,
                                 Integer limit,
                                 String nickname,
                                 String orderBy,
                                 Boolean isAsc,
                                 Long id,
                                 Integer status);

    public AjaxResult updateStatus(Long id, Integer status);

    public AjaxResult createByAdmin(Long id,
                                    String phone,
                                    String password,
                                    Integer status,
                                    String nickname
    );

    public AjaxResult delete(Long id);
}
