package com.mib.configuration.bprconfigurationsvc.controllers.mobile;

import com.mib.configuration.bprconfigurationsvc.dto.response.CustomersCareResponse;
import com.mib.configuration.bprconfigurationsvc.dto.response.GeneralResponse;
import com.mib.configuration.bprconfigurationsvc.services.CustomersCareService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/layanan/pelanggan")
@AllArgsConstructor
public class CustomerCareMobileController {

    private final CustomersCareService customersCareService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeneralResponse<List<CustomersCareResponse>> all() {
        List<CustomersCareResponse> result = customersCareService.listAll();
        return new GeneralResponse<List<CustomersCareResponse>>().success(result);
    }

}
