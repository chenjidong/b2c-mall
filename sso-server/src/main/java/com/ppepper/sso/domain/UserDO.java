package com.ppepper.sso.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 * 会员 或第三方用户 信息 可以有多个
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("b2c_user")
public class UserDO extends SuperDO {

    @TableField("account_id")
    private Long accountId;

    @TableField("open_id")
    private String openId;

    private String nickname;

    @TableField("avatar_url")
    private String avatarUrl;

    private Integer level;

    private Date birthday;

    private Integer gender;

    /**
     * 用户来源 类型  例：QQ  WECHAT  ALI
     */
    private String platform;

}
