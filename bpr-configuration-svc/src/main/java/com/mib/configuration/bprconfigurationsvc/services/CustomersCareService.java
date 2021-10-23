package com.mib.configuration.bprconfigurationsvc.services;

import com.mib.configuration.bprconfigurationsvc.dto.request.CustomersCareRequest;
import com.mib.configuration.bprconfigurationsvc.dto.response.CustomersCareResponse;
import com.mib.configuration.bprconfigurationsvc.entities.CustomersCareEntity;
import com.mib.configuration.bprconfigurationsvc.mapper.CommonMapper;
import com.mib.configuration.bprconfigurationsvc.repositories.CustomersCareRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class CustomersCareService {

    private final CustomersCareRepo customersCareRepo;
    private final CommonMapper commonMapper;

    public CustomersCareResponse add(CustomersCareRequest customersCareRequest) {
        CustomersCareEntity customersCareEntity = new CustomersCareEntity();
        customersCareEntity.setCode(customersCareRequest.getCode());
        customersCareEntity.setName(customersCareRequest.getName());
        customersCareEntity.setDescription(customersCareRequest.getDescription());
        customersCareEntity.setUuid(UUID.randomUUID());
        var customerCare = customersCareRepo.save(customersCareEntity);

        return CustomersCareResponse.builder()
                .code(customerCare.getCode())
                .description(customerCare.getDescription())
                .name(customerCare.getName())
                .uuid(customerCare.getUuid())
                .build();
    }

    public List<CustomersCareResponse> listAll() {
        List<CustomersCareEntity> allCustomerCare = (List<CustomersCareEntity>) customersCareRepo.findAll();

        List<CustomersCareResponse> listResponse = commonMapper.mapList(allCustomerCare, CustomersCareResponse.class);
        return listResponse;
    }

    public Page<CustomersCareResponse> searchList(Pageable pageable) {
        Page<CustomersCareEntity> customersCarePage = customersCareRepo.searchList(pageable);

        Page<CustomersCareResponse> customersCareResponsePage = commonMapper.mapEntityPageIntoDtoPage(customersCarePage, CustomersCareResponse.class);
        return customersCareResponsePage;
    }

}
