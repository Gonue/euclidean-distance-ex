package com.template.server.domain.direction.service;

import com.template.server.domain.cvstore.service.CvStoreSearchService;
import com.template.server.domain.direction.entity.Direction;
import com.template.server.domain.kakao.dto.KakaoDocumentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DirectionService {

    private static final int MAX_SEARCH_COUNT = 3;
    private static final double RADIUS_KM = 10.0;

    private final CvStoreSearchService cvStoreSearchService;

    public List<Direction> buildDirectionList(KakaoDocumentDto kakaoDocumentDto){

        if(Objects.isNull(kakaoDocumentDto)) return Collections.emptyList();

        return cvStoreSearchService.searchCvStoreDtoList()
                .stream().map(cvStoreDto ->
                        Direction.builder()
                                .inputAddress(kakaoDocumentDto.getAddressName())
                                .inputLatitude(kakaoDocumentDto.getLatitude())
                                .inputLongitude(kakaoDocumentDto.getLongitude())
                                .targetPharmacyName(cvStoreDto.getCvStoreName())
                                .targetAddress(cvStoreDto.getCvStoreAddress())
                                .targetLatitude(cvStoreDto.getLatitude())
                                .targetLongitude(cvStoreDto.getLongitude())
                                .distance(
                                        calculateDistance(kakaoDocumentDto.getLatitude(), kakaoDocumentDto.getLongitude(),
                                                cvStoreDto.getLatitude(), cvStoreDto.getLongitude()))
                                .build())
                .filter(direction -> direction.getDistance() <= RADIUS_KM)
                .sorted(Comparator.comparing(Direction::getDistance))
                .limit(MAX_SEARCH_COUNT)
                .collect(Collectors.toList());
    }



    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371;
        return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }
}
