package com.mib.transfer.bprtransfersvc.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/transfer/")
public class TransferController {

    private final Environment environment;

    @GetMapping("/status/check")
    public ResponseEntity<String> status() {
        return ResponseEntity.status(HttpStatus.OK).body("Working..." + environment.getProperty("local.server.port") + "--"
                + environment.getProperty("spring.cloud.client.ip-address"));
    }

}
