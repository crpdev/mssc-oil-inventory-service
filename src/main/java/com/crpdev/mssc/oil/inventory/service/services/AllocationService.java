package com.crpdev.mssc.oil.inventory.service.services;

import com.crpdev.factory.oil.model.OilOrderDto;

/**
 * Created by rajapandian
 * Date: 03/09/20
 * Project: mssc-oil-inventory-service
 * Package: com.crpdev.mssc.oil.inventory.service.services
 **/
public interface AllocationService {
    Boolean allocateOrder (OilOrderDto oilOrderDto);

    void deAllocateOrder (OilOrderDto oilOrderDto);
}
