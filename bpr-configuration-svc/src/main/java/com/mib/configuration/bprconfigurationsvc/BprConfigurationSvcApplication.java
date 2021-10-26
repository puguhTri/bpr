package com.mib.configuration.bprconfigurationsvc;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.IOException;

@SpringBootApplication
@EnableDiscoveryClient
public class BprConfigurationSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BprConfigurationSvcApplication.class, args);

    }

}
