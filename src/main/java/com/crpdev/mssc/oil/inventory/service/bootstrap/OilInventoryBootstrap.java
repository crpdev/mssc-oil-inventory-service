package com.crpdev.mssc.oil.inventory.service.bootstrap;

import com.crpdev.mssc.oil.inventory.service.domain.OilInventory;
import com.crpdev.mssc.oil.inventory.service.repository.OilInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class OilInventoryBootstrap implements CommandLineRunner {

    public static final String OIL_1_BARCODE = "0631234200036";
    public static final String OIL_2_BARCODE = "0631234300019";
    public static final String OIL_3_BARCODE = "0083783375213";
    public static final UUID OIL_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID OIL_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID OIL_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final OilInventoryRepository oilInventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (oilInventoryRepository.count() == 0){
            loadInventoryData();
        }
    }

    private void loadInventoryData() {
        oilInventoryRepository.save(OilInventory
                .builder()
                .oilId(OIL_1_UUID)
                .barCode(OIL_1_BARCODE)
                .quantityInHand(50)
                .build());

        oilInventoryRepository.save(OilInventory
                .builder()
                .oilId(OIL_2_UUID)
                .barCode(OIL_2_BARCODE)
                .quantityInHand(50)
                .build());

        oilInventoryRepository.save(OilInventory
                .builder()
                .oilId(OIL_2_UUID)
                .barCode(OIL_2_BARCODE)
                .quantityInHand(50)
                .build());

        log.debug("Loaded Inventory. Record count: " + oilInventoryRepository.count());
    }
    }
