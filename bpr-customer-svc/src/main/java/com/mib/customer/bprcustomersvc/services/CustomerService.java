package com.mib.customer.bprcustomersvc.services;

import com.mib.customer.bprcustomersvc.constans.DefaultMessage;
import com.mib.customer.bprcustomersvc.dto.response.ProfileResponse;
import com.mib.customer.bprcustomersvc.exceptions.FlowException;
import com.mib.customer.bprcustomersvc.repositories.CustomerRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;

    public ProfileResponse getProfile(UUID customerId) {
        var customerEntity = customerRepo.findByCustomerId(customerId).orElseThrow(() -> (new FlowException(DefaultMessage.NOT_FOUND)));
        return ProfileResponse.builder()
                .accountNumber(customerEntity.getAccountNumber())
                .email(customerEntity.getEmail())
                .name(customerEntity.getName())
                .build();
    }

}
