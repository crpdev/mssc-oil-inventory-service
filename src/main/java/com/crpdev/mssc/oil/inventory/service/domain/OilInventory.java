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
    public OilInventory(UUID id, Integer version, Timestamp createdDate, Timestamp lastModifiedDate, UUID oilId, String productCode, Integer quantityOnHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.oilId = oilId;
        this.productCode = productCode;
        this.quantityOnHand = quantityOnHand;
    }

    private UUID oilId;
    private String productCode;
    private Integer quantityOnHand = 0;
}
