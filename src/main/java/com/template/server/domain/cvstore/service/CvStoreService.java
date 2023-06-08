package com.template.server.domain.cvstore.service;

import com.template.server.domain.cvstore.entity.CvStore;
import com.template.server.domain.cvstore.repository.CvStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CvStoreService {

    private final CvStoreRepository cvStoreRepository;

    @Transactional
    public void updateAddress(Long id, String address){
        CvStore cvStore = cvStoreRepository.findById(id).orElseThrow(null);
        if(Objects.isNull(cvStore)){
            return;
        }

        cvStore.changeAddress(address);
    }
}
