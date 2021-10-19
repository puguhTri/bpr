package com.mib.customer.bprcustomersvc.integration.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestModel {

    private String firstName;
    private String lastName;

    private String password;

    private String email;
    private String username;
}
