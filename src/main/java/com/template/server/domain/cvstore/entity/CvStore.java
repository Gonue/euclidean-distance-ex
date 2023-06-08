package com.template.server.domain.cvstore.entity;


import com.template.server.global.audit.AuditingFields;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Table(name = "cv_store")
@Entity
public class CvStore extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cvStoreName;
    private String cvStoreAddress;
    private double latitude;
    private double longitude;

    public void changeAddress(String address){
        this.cvStoreAddress = address;
    }
}
