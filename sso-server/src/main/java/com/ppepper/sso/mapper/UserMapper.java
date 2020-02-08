package com.ppepper.sso.mapper;


import com.ppepper.common.dto.UserDTO;
import com.ppepper.common.mapper.BaseMapper;
import com.ppepper.sso.domain.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
public interface UserMapper extends BaseMapper<UserDO> {

    public UserDTO login(@Param("phone") String phone, @Param("cryptPassword") String cryptPassword);

    public List<UserDO> getUserList(
            @Param("id") Long id, @Param("nickname") String nickname,
            @Param("level") Integer level, @Param("gender") Integer gender,
            @Param("status") Integer status, @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    public Integer countUser(
            @Param("id") Long id, @Param("nickname") String nickname,
            @Param("level") Integer level, @Param("gender") Integer gender,
            @Param("status") Integer status);
}
