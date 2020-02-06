package com.ppepper.order.mapper;


import com.ppepper.common.dto.OrderDTO;
import com.ppepper.common.mapper.BaseMapper;
import com.ppepper.order.domain.OrderDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public interface OrderMapper extends BaseMapper<OrderDO> {

    public List<OrderDTO> selectOrderPage(@Param("status") Integer status, @Param("offset") Integer offset, @Param("limit") Integer limit, @Param("userId") Long userId);

    public Long countOrder(@Param("status") Integer status, @Param("offset") Integer offset, @Param("limit") Integer limit, @Param("userId") Long userId);

    public List<String> selectExpireOrderNos(@Param("status") Integer status, @Param("time") Date time);

}
