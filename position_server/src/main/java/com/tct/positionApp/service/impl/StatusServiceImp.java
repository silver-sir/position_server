package com.tct.positionApp.service.impl;

import com.tct.positionApp.domain.Status;
import com.tct.positionApp.service.StatusService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("StatusService")
public class StatusServiceImp implements StatusService {


    @Override
    @Cacheable(value = "first")
    public Status getStatus(int id) {
        System.out.println("----模拟获取数据----");
        Status status = new Status();
        status.setId(id);
        status.setStatusName("qqq");
        return status;
    }

    @Override
    @CacheEvict
    public void deleteStatus(int id) {
        System.out.println("----删除类----");
    }
}
