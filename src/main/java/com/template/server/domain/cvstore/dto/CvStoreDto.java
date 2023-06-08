package com.template.server.domain.cvstore.dto;

import com.template.server.domain.cvstore.entity.CvStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CvStoreDto {
    private Long id;
    private String cvStoreName;
    private String cvStoreAddress;
    private double latitude;
    private double longitude;


    public static CvStoreDto from(CvStore entity){
        return new CvStoreDto(
                entity.getId(),
                entity.getCvStoreName(),
                entity.getCvStoreAddress(),
                entity.getLatitude(),
                entity.getLongitude()
        );
    }
}
