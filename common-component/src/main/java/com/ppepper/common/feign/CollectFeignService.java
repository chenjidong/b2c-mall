package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.CollectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@Service
public class CollectFeignService extends BaseFeignService {

    @Autowired
    private AccountFeignClient accountFeignClient;

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public CollectDTO getCollect(Long id) {
        if (StringUtils.isEmpty(id))
            return null;
        return convert(accountFeignClient.getCollect(id), CollectDTO.class);
    }

    public CollectDTO serviceOffline(Long id) {
        return null;
    }
}
