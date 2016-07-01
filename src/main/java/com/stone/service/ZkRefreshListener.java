package com.stone.service;

import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Created by Young on 2016-01-24.
 */
@Service
public class ZkRefreshListener implements ApplicationListener<RefreshScopeRefreshedEvent> {
    @Override
    public void onApplicationEvent(RefreshScopeRefreshedEvent event) {
        System.out.println("reload!!!!!");
    }
}
