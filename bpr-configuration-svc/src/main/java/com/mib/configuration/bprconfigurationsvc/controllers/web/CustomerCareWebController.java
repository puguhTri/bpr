package com.mib.configuration.bprconfigurationsvc.controllers.web;

import com.mib.configuration.bprconfigurationsvc.dto.request.CustomersCareRequest;
import com.mib.configuration.bprconfigurationsvc.dto.response.CustomersCareResponse;
import com.mib.configuration.bprconfigurationsvc.dto.response.GeneralResponse;
import com.mib.configuration.bprconfigurationsvc.services.CustomersCareService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/web/layanan/pelanggan")
@AllArgsConstructor
public class CustomerCareWebController {

    private final CustomersCareService customersCareService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<CustomersCareResponse> add(@RequestBody CustomersCareRequest customersCareRequest) {
        var addResponse = customersCareService.add(customersCareRequest);
        return new GeneralResponse<CustomersCareResponse>().success(addResponse);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeneralResponse<Page<CustomersCareResponse>> list(Pageable pageable) {
        Page<CustomersCareResponse> result = customersCareService.searchList(pageable);
        return new GeneralResponse<Page<CustomersCareResponse>>().success(result);
    }

}
