package com.tech.repair.service;

import com.tech.repair.po.System;
import com.tech.repair.repository.SystemRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SystemService {

    final private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemRepository systemRepository;

    public System getSystemInfo()
    {
        return systemRepository.findAllById(1);
    }

    public System updateSystemInfo(System system)
    {
        if (Strings.isNotBlank(system.getSystemImages())) {
            return (System) systemRepository.save(system);
        }
        else
        {
            logger.warn( "更新信息--参数错误");
            return null;
        }
    }

}
