package com.alerthub.loaderservice.service.impl;

import com.alerthub.loaderservice.entity.PlatformInformation;
import com.alerthub.loaderservice.repository.PlatformInformationRepository;
import com.alerthub.loaderservice.service.PlatformInformationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformInformationServiceImpl implements PlatformInformationService {

    private final PlatformInformationRepository repository;

    public PlatformInformationServiceImpl(PlatformInformationRepository repository) {
        this.repository = repository;
    }

    @Override
    public PlatformInformation savePlatformInfo(PlatformInformation info) {
        return repository.save(info);
    }

    @Override
    public List<PlatformInformation> getAllPlatformInfo() {
        return repository.findAll();
    }
}
