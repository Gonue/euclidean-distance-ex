package com.template.server.domain.direction.entity;

import com.template.server.global.audit.AuditingFields;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Table(name = "direction")
@Entity
public class Direction extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputAddress;
    private double inputLatitude;
    private double inputLongitude;

    private String targetPharmacyName;
    private String targetAddress;
    private double targetLatitude;
    private double targetLongitude;

    private double distance;
}
