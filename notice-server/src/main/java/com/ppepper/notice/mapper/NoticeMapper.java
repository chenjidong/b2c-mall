package com.ppepper.notice.mapper;


import com.ppepper.common.dto.NoticeDTO;
import com.ppepper.common.mapper.BaseMapper;
import com.ppepper.notice.domain.NoticeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface NoticeMapper extends BaseMapper<NoticeDO> {

    public List<NoticeDTO> selectNoticePage(@Param("offset") Integer offset, @Param("limit") Integer limit);

    public Long countNotice();

}
