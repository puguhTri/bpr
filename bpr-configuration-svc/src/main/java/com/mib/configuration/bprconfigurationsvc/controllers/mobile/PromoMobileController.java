package com.mib.configuration.bprconfigurationsvc.controllers.mobile;

import com.mib.configuration.bprconfigurationsvc.dto.response.CustomersCareResponse;
import com.mib.configuration.bprconfigurationsvc.dto.response.GeneralResponse;
import com.mib.configuration.bprconfigurationsvc.dto.response.PromoListResponse;
import com.mib.configuration.bprconfigurationsvc.services.CustomersCareService;
import com.mib.configuration.bprconfigurationsvc.services.PromoService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/promo")
@AllArgsConstructor
public class PromoMobileController {

    private final PromoService promoService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeneralResponse<List<PromoListResponse>> all() {
        List<PromoListResponse> result = promoService.listAll();
        return new GeneralResponse<List<PromoListResponse>>().success(result);
    }

}
