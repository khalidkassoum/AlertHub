package com.alerthub.loaderservice.repository;

import com.alerthub.loaderservice.entity.PlatformInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformInformationRepository extends JpaRepository<PlatformInformation, Integer> {
    // You can add custom queries here later
}
