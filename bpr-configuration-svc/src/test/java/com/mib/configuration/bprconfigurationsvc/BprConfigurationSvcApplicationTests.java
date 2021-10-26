package com.mib.configuration.bprconfigurationsvc;

import com.mib.configuration.bprconfigurationsvc.utils.ImageKitUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BprConfigurationSvcApplicationTests {

	@Autowired
	ImageKitUtil imageKitUtil;

	@Test
	void contextLoads() throws Exception {
		//imageKitUtil.upload();
	}

}
