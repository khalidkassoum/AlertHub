package com.alerthub.loaderservice.service;

import com.alerthub.loaderservice.entity.PlatformInformation;

import java.util.List;

public interface PlatformInformationService {
    PlatformInformation savePlatformInfo(PlatformInformation info);
    List<PlatformInformation> getAllPlatformInfo();
}
