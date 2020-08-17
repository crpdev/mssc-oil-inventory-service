package com.crpdev.mssc.oil.inventory.service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OilInventory extends BaseEntity {

    @Builder
    public OilInventory(UUID id, Integer version, Timestamp createdDate, Timestamp lastModifiedDate, UUID oilId, String barCode, Integer quantityInHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.oilId = oilId;
        this.barCode = barCode;
        this.quantityInHand = quantityInHand;
    }

    private UUID oilId;
    private String barCode;
    private Integer quantityInHand = 0;
}
