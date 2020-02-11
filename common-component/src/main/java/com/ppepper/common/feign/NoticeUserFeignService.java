package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-10
 */
@Service
public class NoticeUserFeignService extends BaseFeignService {

    @Autowired
    private NoticeFeignClient noticeFeignClient;

    @HystrixCommand(fallbackMethod = "serviceOffline")//服务不可用时  hystrix 自动调用指定函数返回
    public NoticeDTO get(Long id) {
        return convert(noticeFeignClient.getByUserId(id), NoticeDTO.class);
    }

    public NoticeDTO serviceOffline(Long id) {
        return null;
    }
}
