package com.mib.customer.bprcustomersvc.services;

import com.mib.customer.bprcustomersvc.constans.DefaultMessage;
import com.mib.customer.bprcustomersvc.dto.request.*;
import com.mib.customer.bprcustomersvc.dto.response.*;
import com.mib.customer.bprcustomersvc.entities.CustomerEntity;
import com.mib.customer.bprcustomersvc.entities.CustomerMpinEntity;
import com.mib.customer.bprcustomersvc.entities.CustomerOtpEntity;
import com.mib.customer.bprcustomersvc.exceptions.FlowException;
import com.mib.customer.bprcustomersvc.integration.auth.AuthServiceClient;
import com.mib.customer.bprcustomersvc.integration.auth.LoginRequestModel;
import com.mib.customer.bprcustomersvc.integration.auth.UserRequestModel;
import com.mib.customer.bprcustomersvc.integration.auth.UserResponseModel;
import com.mib.customer.bprcustomersvc.mapper.CustomerAuthMapper;
import com.mib.customer.bprcustomersvc.repositories.CustomerMpinRepo;
import com.mib.customer.bprcustomersvc.repositories.CustomerOtpRepo;
import com.mib.customer.bprcustomersvc.repositories.CustomerRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class CustomerAuthService {

    private final CustomerAuthMapper customerMapper;
    private final CustomerRepo customerRepo;
    private final CustomerOtpRepo customerOtpRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthServiceClient authServiceClient;
    private final CustomerMpinRepo customerMpinRepo;

    public RegisterResponse register(RegisterRequest registerRequest) {
        var customerEntity = customerMapper.toCustomer(registerRequest);
        customerEntity.setCustomerId(UUID.randomUUID());
        var customerResponse = customerRepo.save(customerEntity);

        String otpCode = new String(generateOtp(4));


        CustomerOtpEntity customerOtp = new CustomerOtpEntity();
        customerOtp.setCustomer(customerEntity);
        customerOtp.setCode(otpCode);
        customerOtp.setCodeEncryption(bCryptPasswordEncoder.encode(otpCode));
        customerOtp.setCounter(0);
        customerOtpRepo.save(customerOtp);

        return RegisterResponse.builder()
                .customerId(customerEntity.getCustomerId())
                .email(customerResponse.getEmail())
                .phoneNumber(customerResponse.getPhoneNumber())
                .otp(otpCode)
                .build();
    }

    public VerifyResponse verifyOtp(VerifyRequest verifyRequest) {
        var customerEntity = customerRepo.findByCustomerId(verifyRequest.getCustomerId()).orElseThrow(() -> new FlowException("Data tidak di temukan"));
        var customerOtpEntity = customerOtpRepo.findByCustomer(customerEntity).orElseThrow(() -> new FlowException("Data tidak di temukan"));
        if (bCryptPasswordEncoder.matches(verifyRequest.getOtp(), customerOtpEntity.getCodeEncryption())) {
            return VerifyResponse.builder()
                    .customerId(customerEntity.getCustomerId())
                    .name(customerEntity.getName())
                    .email(customerEntity.getEmail())
                    .phoneNumber(customerEntity.getPhoneNumber())
                    .build();
        } else {
            throw new FlowException("Verifikasi gagal");
        }
    }

    public PasswordSettingResponse setPassword(PasswordSettingRequest passwordSettingRequest) {
        var customerEntity = customerRepo.findByCustomerId(passwordSettingRequest.getCustomerId()).orElseThrow(() -> (new FlowException("Data tidak ditemukan")));

        UserRequestModel userRequestModel = new UserRequestModel();
        userRequestModel.setPassword(passwordSettingRequest.getPassword());
        userRequestModel.setEmail(customerEntity.getEmail());
        userRequestModel.setUsername(passwordSettingRequest.getUsername()
        );
        userRequestModel.setFirstName(customerEntity.getName());
        UserResponseModel userResponseModel = authServiceClient.createUser(userRequestModel);

        customerEntity.setUserId(UUID.fromString(userResponseModel.getUserId()));
        customerEntity.setUsername(passwordSettingRequest.getUsername());
        customerRepo.save(customerEntity);

        return PasswordSettingResponse.builder()
                .name(customerEntity.getName())
                .customerId(customerEntity.getCustomerId())
                .email(customerEntity.getEmail())
                .build();
    }


    @Transactional
    public MpinResponse setMpin(MpinRequest mpinRequest) {
        CustomerEntity customerEntity = customerRepo.findByCustomerId(mpinRequest.getCustomerId()).orElseThrow();
        if (mpinRequest.getMpin().equals(mpinRequest.getMpinConfirm())) {
            CustomerMpinEntity customerMpinEntity = new CustomerMpinEntity();
            customerMpinEntity.setCustomer(customerEntity);
            customerMpinEntity.setPin(mpinRequest.getMpin().toString());
            customerMpinEntity.setPinEncryption(bCryptPasswordEncoder.encode(customerMpinEntity.getPin()));
            customerMpinRepo.save(customerMpinEntity);

            customerEntity.setHasPin(true);
            customerRepo.save(customerEntity);
            return MpinResponse.builder()
                    .customerId(customerEntity.getCustomerId())
                    .build();
        } else {
            throw new FlowException("Pin tidak sama");
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var customerEntity = customerRepo.findByUsername(loginRequest.getUsername()).orElseThrow(() -> (new FlowException(DefaultMessage.NOT_FOUND)));

        ResponseEntity<Void> response = authServiceClient.loginUser(LoginRequestModel.builder()
                .username(loginRequest.getUsername())
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build());

        String token = response.getHeaders().getFirst("token");
        String expireIn = response.getHeaders().getFirst("expire_in");

        return LoginResponse.builder()
                .name(customerEntity.getName())
                .customerId(customerEntity.getCustomerId())
                .accessToken(token)
                .expireIn(expireIn)
                .hasPin(customerEntity.getHasPin())
                .build();
    }


    /**
     * ======= private methode, function
     */

    private char[] generateOtp(int len) {
        String numbers = "0123456789";

        Random rndm_method = new Random();

        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            otp[i] =
                    numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }


}
