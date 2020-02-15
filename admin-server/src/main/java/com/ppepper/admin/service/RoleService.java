package com.ppepper.admin.service;

import com.ppepper.common.dto.RoleDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
public interface RoleService {

    AjaxResult get(Long id);

    public AjaxResult options();

    Page<RoleDTO> list(Integer page, Integer limit, String name, @RequestParam("sort") String orderBy, @RequestParam("order") Boolean isAsc);

    public AjaxResult create(RoleDTO roleDTO);

    public AjaxResult delete(Long id);
}
