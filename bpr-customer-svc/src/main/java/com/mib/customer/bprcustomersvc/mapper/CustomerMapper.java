package com.mib.customer.bprcustomersvc.mapper;


import com.mib.customer.bprcustomersvc.dto.request.RegisterRequest;
import com.mib.customer.bprcustomersvc.entities.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public ModelMapper getMapper() {
        return modelMapper;
    }

    public CustomerEntity toCustomer(RegisterRequest registerRequest) {
        return modelMapper.map(registerRequest, CustomerEntity.class);
    }
}
