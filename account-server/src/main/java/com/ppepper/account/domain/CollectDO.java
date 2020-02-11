package com.ppepper.account.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("b2c_collect")
public class CollectDO extends SuperDO {
    @TableField("account_id")
    private Long accountId;

    @TableField("collect_id")
    private Long collectId;

    private Integer type;

}
