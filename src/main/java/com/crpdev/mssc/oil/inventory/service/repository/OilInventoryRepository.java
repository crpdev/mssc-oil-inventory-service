package com.crpdev.mssc.oil.inventory.service.repository;

import com.crpdev.mssc.oil.inventory.service.domain.OilInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OilInventoryRepository extends JpaRepository<OilInventory, UUID> {

    List<OilInventory> findAllByOilId(UUID oilId);

    List<OilInventory> findAllByProductCode(String productCode);
}
