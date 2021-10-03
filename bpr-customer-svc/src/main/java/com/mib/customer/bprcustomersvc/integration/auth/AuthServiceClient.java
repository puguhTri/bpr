package com.mib.customer.bprcustomersvc.integration.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users-ws")
public interface AuthServiceClient {

    @PostMapping(value = "/api/users/register")
    public UserResponseModel createUser(@RequestBody UserRequestModel userRequestModel);

    @PostMapping("/users/login")
    ResponseEntity<Void> loginUser(@RequestBody LoginRequestModel loginRequestModel);
}
