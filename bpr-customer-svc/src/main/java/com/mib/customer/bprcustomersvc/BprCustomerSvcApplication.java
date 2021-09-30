package com.mib.customer.bprcustomersvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BprCustomerSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BprCustomerSvcApplication.class, args);
    }

}
