package com.mib.configuration.bprconfigurationsvc.controllers.web;

import com.mib.configuration.bprconfigurationsvc.dto.request.PromoAddRequest;
import com.mib.configuration.bprconfigurationsvc.dto.response.GeneralResponse;
import com.mib.configuration.bprconfigurationsvc.dto.response.PromoAddResponse;
import com.mib.configuration.bprconfigurationsvc.dto.response.PromoFileResponse;
import com.mib.configuration.bprconfigurationsvc.dto.response.PromoListResponse;
import com.mib.configuration.bprconfigurationsvc.services.PromoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/web/promo")
@AllArgsConstructor
public class PromoWebController {

    private final PromoService promoService;

    @PostMapping("/upload-file")
    public GeneralResponse<PromoFileResponse> uploadMessageFile(
            @RequestParam("file") MultipartFile multipartFile) throws Exception {

        PromoFileResponse promoFileResponse = promoService.uploadFile(multipartFile, multipartFile.getContentType());
        return new GeneralResponse<PromoFileResponse>().success(promoFileResponse);
    }

    @PostMapping("/add")
    public GeneralResponse<PromoAddResponse> add(@RequestBody PromoAddRequest promoAddRequest) throws Exception {
        PromoAddResponse promoAddResponse = promoService.add(promoAddRequest);
        return new GeneralResponse<PromoAddResponse>().success(promoAddResponse);
    }

    @GetMapping("/list")
    public GeneralResponse<Page<PromoListResponse>> add(Pageable pageable) throws Exception {
        Page<PromoListResponse> promoListResponsePage = promoService.searchList(pageable);
        return new GeneralResponse<Page<PromoListResponse>>().success(promoListResponsePage);
    }

}
