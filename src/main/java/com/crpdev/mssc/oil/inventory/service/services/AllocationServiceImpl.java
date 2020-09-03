package com.crpdev.mssc.oil.inventory.service.services;

import com.crpdev.factory.oil.model.OilOrderDto;
import com.crpdev.factory.oil.model.OilOrderLineDto;
import com.crpdev.mssc.oil.inventory.service.domain.OilInventory;
import com.crpdev.mssc.oil.inventory.service.repository.OilInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rajapandian
 * Date: 03/09/20
 * Project: mssc-oil-inventory-service
 * Package: com.crpdev.mssc.oil.inventory.service.services
 **/

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationServiceImpl implements AllocationService {

    private final OilInventoryRepository oilInventoryRepository;

    @Override
    public Boolean allocateOrder(OilOrderDto oilOrderDto) {
        log.debug("Allocating OrderId: " + oilOrderDto.getId());

        AtomicInteger totalOrdered = new AtomicInteger();
        AtomicInteger totalAllocated = new AtomicInteger();

        oilOrderDto.getOilOrderLines().forEach(orderLine -> {
            if ((((orderLine.getOrderQuantity() != null ? orderLine.getOrderQuantity() : 0)
                    - (orderLine.getQuantityAllocated() != null ? orderLine.getQuantityAllocated() : 0)) > 0)) {
                allocateOilOrderLine(orderLine);
            }
            totalOrdered.set(totalOrdered.get() + orderLine.getOrderQuantity());
            totalAllocated.set(totalAllocated.get() + (null != orderLine.getOrderQuantity()? orderLine.getOrderQuantity() : 0));
        });

        log.debug("Total Ordered: " + totalOrdered.get() + " > Total Allocated: " + totalAllocated.get());

        return totalOrdered.get() == totalAllocated.get();
    }

    private void allocateOilOrderLine(OilOrderLineDto oilOrderLineDto){
        List<OilInventory> oilInventoryList = oilInventoryRepository.findAllByProductCode(oilOrderLineDto.getProductCode());
        oilInventoryList.forEach(oilInventory -> {
            int inventory = (oilInventory.getQuantityOnHand() == null) ? 0 : oilInventory.getQuantityOnHand();
            int orderQty = (oilOrderLineDto.getOrderQuantity() == null) ? 0 : oilOrderLineDto.getOrderQuantity();
            int allocatedQty = (oilOrderLineDto.getQuantityAllocated() == null) ? 0 : oilOrderLineDto.getQuantityAllocated();
            int qtyToAllocate = orderQty - allocatedQty;

            if (inventory >= qtyToAllocate) { // full allocation
                inventory = inventory - qtyToAllocate;
                oilOrderLineDto.setQuantityAllocated(orderQty);
                oilInventory.setQuantityOnHand(inventory);

                oilInventoryRepository.save(oilInventory);
            } else if (inventory > 0) { //partial allocation
                oilOrderLineDto.setQuantityAllocated(allocatedQty + inventory);
                oilInventory.setQuantityOnHand(0);

            }

            if (oilInventory.getQuantityOnHand() == 0) {
                oilInventoryRepository.delete(oilInventory);
            }
        });
    }
}
