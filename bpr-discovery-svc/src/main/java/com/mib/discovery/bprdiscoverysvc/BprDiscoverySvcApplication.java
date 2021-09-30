package com.mib.discovery.bprdiscoverysvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BprDiscoverySvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BprDiscoverySvcApplication.class, args);
    }

}
