package com.template.server.domain.cvstore.service;

import com.template.server.domain.cvstore.dto.CvStoreDto;
import com.template.server.domain.cvstore.repository.CvStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class CvStoreSearchService {

    private final CvStoreService cvStoreService;
    private final CvStoreRepository cvStoreRepository;

    public List<CvStoreDto> searchCvStoreDtoList(){
        return cvStoreRepository.findAll()
                .stream()
                .map(CvStoreDto::from)
                .collect(Collectors.toList());
    }
}
