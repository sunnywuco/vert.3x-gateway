package com.stone.service;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicWatchedConfiguration;
import com.netflix.config.source.ZooKeeperConfigurationSource;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * https://github.com/Netflix/archaius/wiki/ZooKeeper-Dynamic-Configuration
 * Created by Young on 2016-01-24.
 */
@Component
public class ArchaiusZkConfig {

    @Autowired
    CuratorFramework client;

    @Value("${spring.application.name}")
    String appName;

    @PostConstruct
    public void installZkConfig() throws Exception {
        String zkConfigRootPath = "/config/"+appName;
        ZooKeeperConfigurationSource zkConfigSource = new ZooKeeperConfigurationSource(client, zkConfigRootPath);
        zkConfigSource.start();
        DynamicWatchedConfiguration zkDynamicConfig = new DynamicWatchedConfiguration(zkConfigSource);
        ConfigurationManager.install(zkDynamicConfig);
    }

    public String getDynamicUpdate(){
        String myProperty = DynamicPropertyFactory.getInstance()
                .getStringProperty("msg", "<none>")
                .get();
        return myProperty;
    }
}
