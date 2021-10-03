package com.mib.customer.bprcustomersvc.integration.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;

}
