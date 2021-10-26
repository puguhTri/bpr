package com.mib.configuration.bprconfigurationsvc.utils;

import com.mib.configuration.bprconfigurationsvc.BprConfigurationSvcApplication;
import com.mib.configuration.bprconfigurationsvc.dto.response.PromoFileResponse;
import com.mib.configuration.bprconfigurationsvc.entities.MediaEntity;
import com.mib.configuration.bprconfigurationsvc.repositories.MediaRepo;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import io.imagekit.sdk.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Log4j2
public class ImageKitUtil implements CommandLineRunner {

    @Autowired
    private MediaRepo mediaRepo;


    ImageKit imageKit;


    @Override
    public void run(String... args) throws Exception {
        ImageKit imageKit = ImageKit.getInstance();
        Configuration config = Utils.getSystemConfig(BprConfigurationSvcApplication.class);
        imageKit.setConfig(config);
        this.imageKit = imageKit;
    }


    public PromoFileResponse upload(MultipartFile file, String fileName, String folderName) throws Exception {

        byte[] bytes = file.getBytes();
        FileCreateRequest fileCreateRequest = new FileCreateRequest(bytes, fileName);
        String customCoordinates = "10,10,20,20";
        fileCreateRequest.setCustomCoordinates(customCoordinates);  // optional
        fileCreateRequest.setFolder(folderName);  // optional
        fileCreateRequest.setPrivateFile(false);  // optional
        fileCreateRequest.setUseUniqueFileName(true);  // optional

        Result result = imageKit.upload(fileCreateRequest);

        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setFileId(result.getFileId());
        mediaEntity.setUrl(result.getUrl());
        mediaEntity.setFileName(result.getName());
        mediaRepo.save(mediaEntity);

        return PromoFileResponse.builder()
                .fileId(result.getFileId())
                .url(result.getUrl())
                .build();
    }


}
