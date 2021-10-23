package com.mib.customer.bprcustomersvc.services;

import com.mib.customer.bprcustomersvc.constans.DefaultMessage;
import com.mib.customer.bprcustomersvc.dto.request.CheckMpinRequest;
import com.mib.customer.bprcustomersvc.dto.response.CheckMpinResponse;
import com.mib.customer.bprcustomersvc.dto.response.CustomerWebResponse;
import com.mib.customer.bprcustomersvc.dto.response.DetailCustomerResponse;
import com.mib.customer.bprcustomersvc.dto.response.ProfileResponse;
import com.mib.customer.bprcustomersvc.entities.CustomerEntity;
import com.mib.customer.bprcustomersvc.exceptions.FlowException;
import com.mib.customer.bprcustomersvc.mapper.CommonMapper;
import com.mib.customer.bprcustomersvc.repositories.CustomerMpinRepo;
import com.mib.customer.bprcustomersvc.repositories.CustomerRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CommonMapper commonMapper;
    private final CustomerMpinRepo customerMpinRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ProfileResponse getProfile(UUID customerId) {
        var customerEntity = customerRepo.findByCustomerId(customerId).orElseThrow(() -> (new FlowException(DefaultMessage.NOT_FOUND)));
        return ProfileResponse.builder()
                .accountNumber(customerEntity.getAccountNumber())
                .email(customerEntity.getEmail())
                .name(customerEntity.getName())
                .build();
    }

    public CheckMpinResponse chekMpin(CheckMpinRequest checkMpinRequest) {
        var customerEntity = customerRepo.findByCustomerId(checkMpinRequest.getCustomerId()).orElseThrow(() -> (new FlowException(DefaultMessage.NOT_FOUND)));
        var customerMpin = customerMpinRepo.findByCustomer(customerEntity).orElseThrow();
        if (bCryptPasswordEncoder.matches(checkMpinRequest.getMpin(), customerMpin.getPinEncryption())) {
            return CheckMpinResponse.builder()
                    .customerId(customerEntity.getCustomerId())
                    .mpinValidated(true)
                    .name(customerEntity.getName())
                    .status(customerEntity.getStatus())
                    .build();
        }else {
            throw new FlowException("Mpin tidak valid");
        }
    }

    public Page<CustomerWebResponse> listCustomer(Pageable pageable) {
        Page<CustomerEntity> customerEntityPage = customerRepo.findAll(pageable);

        Page<CustomerWebResponse> customerWebResponsePage = commonMapper.mapEntityPageIntoDtoPage(customerEntityPage, CustomerWebResponse.class);
        return customerWebResponsePage;
    }

    public DetailCustomerResponse detailCustomer(UUID uuid) {
        var customer = customerRepo.findByCustomerId(uuid).orElseThrow();
        return DetailCustomerResponse.builder()
                .accountNumber(customer.getAccountNumber())
                .status(customer.getStatus())
                .email(customer.getEmail())
                .name(customer.getName())
                .accountNumber(customer.getAccountNumber())
                .build();
    }

}
