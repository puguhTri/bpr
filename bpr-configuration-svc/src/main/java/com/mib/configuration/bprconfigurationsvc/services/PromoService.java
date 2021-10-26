package com.mib.configuration.bprconfigurationsvc.services;

import com.mib.configuration.bprconfigurationsvc.dto.request.PromoAddRequest;
import com.mib.configuration.bprconfigurationsvc.dto.response.*;
import com.mib.configuration.bprconfigurationsvc.entities.CustomersCareEntity;
import com.mib.configuration.bprconfigurationsvc.entities.MediaEntity;
import com.mib.configuration.bprconfigurationsvc.entities.PromoEntity;
import com.mib.configuration.bprconfigurationsvc.mapper.CommonMapper;
import com.mib.configuration.bprconfigurationsvc.repositories.MediaRepo;
import com.mib.configuration.bprconfigurationsvc.repositories.PromoRepo;
import com.mib.configuration.bprconfigurationsvc.utils.ImageKitUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class PromoService {

    private final ImageKitUtil imageKitUtil;
    private final PromoRepo promoRepo;
    private final MediaRepo mediaRepo;
    private final CommonMapper commonMapper;


    public PromoFileResponse uploadFile(MultipartFile file, String fileType) throws Exception {
        String fileName = file.getOriginalFilename();
        String folderName = "promo";
        PromoFileResponse promoFileResponse = imageKitUtil.upload(file, fileName, folderName);
        return promoFileResponse;
    }

    public PromoAddResponse add(PromoAddRequest promoAddRequest) {
        MediaEntity mediaEntity = mediaRepo.findByFileId(promoAddRequest.getFileId()).orElseThrow();

        PromoEntity promoEntity = new PromoEntity();
        promoEntity.setCode(promoAddRequest.getCode());
        promoEntity.setName(promoAddRequest.getName());
        promoEntity.setDescription(promoAddRequest.getDescription());
        promoEntity.setFileId(promoAddRequest.getFileId());
        promoRepo.save(promoEntity);


        return PromoAddResponse.builder()
                .code(promoEntity.getCode())
                .name(promoEntity.getName())
                .description(promoEntity.getDescription())
                .image(ImageResponse.builder()
                        .fileId(mediaEntity.getFileId())
                        .fileName(mediaEntity.getFileName())
                        .url(mediaEntity.getUrl())
                        .build())
                .build();
    }


    public Page<PromoListResponse> searchList(Pageable pageable) {
        Page<PromoEntity> promoEntityPage = promoRepo.searchList(pageable);

        Page<PromoListResponse> promoListResponsePage = commonMapper.mapEntityPageIntoDtoPage(promoEntityPage, PromoListResponse.class);
        return promoListResponsePage;
    }

    public List<PromoListResponse> listAll() {
        List<PromoEntity> all = (List<PromoEntity>) promoRepo.findAll();

        List<PromoListResponse> listResponse = commonMapper.mapList(all, PromoListResponse.class);
        return listResponse;
    }
}
