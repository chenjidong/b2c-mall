package com.ppepper.admin.controller;

import com.ppepper.admin.service.AdminService;
import com.ppepper.common.controller.BaseController;
import com.ppepper.common.dto.AdminDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;
import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@RestController
@RequestMapping("/admin/admin")
@CrossOrigin
public class AdminController extends BaseController {


    @Autowired
    private AdminService adminService;

    @RequestMapping("/list")
    public Page<AdminDTO> list(Integer page, Integer limit, String username, @RequestParam("sort") String orderBy, @RequestParam("order") Boolean isAsc) {
        return adminService.list(page, limit, username, orderBy, isAsc);
    }


    @RequestMapping("/get")
    public AjaxResult get(Long id) {
        return adminService.get(id);
    }

    @RequestMapping("/delete")
    public AjaxResult delete(Long id) {
        return adminService.delete(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public AjaxResult create(@RequestBody AdminDTO adminDTO) {
        return adminService.create(adminDTO);
    }

    @RequestMapping("/getInfo")
    public AjaxResult getUserInfo() {
        return adminService.get(JwtTokenUtils.getCurrentAccountIdByToken());
    }

    /**
     * 首页统计
     *
     * @return
     */
    @RequestMapping("/dashboard")
    public AjaxResult dashboard() {
        return error("没有数据");
    }


    @RequestMapping("/logout")
    public AjaxResult logout() {
        return success("退出成功");
    }

    @RequestMapping("/changePassword")
    public AjaxResult changePassword(String oldPassword, String newPassword, String newPassword2) {
        return adminService.changePassword(JwtTokenUtils.getCurrentAccountIdByToken(), oldPassword, newPassword, newPassword2);
    }

}
