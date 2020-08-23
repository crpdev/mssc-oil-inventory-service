package com.crpdev.mssc.oil.inventory.service.services;

import com.crpdev.common.events.NewInventoryEvent;
import com.crpdev.mssc.oil.inventory.service.config.JmsConfig;
import com.crpdev.mssc.oil.inventory.service.domain.OilInventory;
import com.crpdev.mssc.oil.inventory.service.repository.OilInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rajapandian
 * Date: 23/08/20
 * Project: mssc-oil-inventory-service
 * Package: com.crpdev.mssc.oil.inventory.service.services
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class NewInventoryListener {

    private final OilInventoryRepository oilInventoryRepository;

    @Transactional
    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event){
        log.debug("Got Inventory: " + event.toString());
        oilInventoryRepository.save(OilInventory.builder()
            .oilId(event.getOilDto().getId())
                .barCode(event.getOilDto().getBarCode())
                .quantityOnHand(event.getOilDto().getQuantityOnHand())
                .build());
    }

}
