package com.lhfioravanso.winesalesimprover.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CacheCleanerService {

    Logger logger = LoggerFactory.getLogger(CacheCleanerService.class);

    @Scheduled(fixedDelayString = "${cache.expiration.in.milliseconds}")
    @CacheEvict(value = { "recommendation", "highest-total-purchases", "highest", "fidelity" }, allEntries = true)
    public void cleanAllCache() {
        logger.info("All cache has been cleaned.");
    }
}
