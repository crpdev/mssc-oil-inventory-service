package com.crpdev.common.events;

import com.crpdev.mssc.oil.inventory.service.web.model.OilDto;
import lombok.NoArgsConstructor;

/**
 * Created by rajapandian
 * Date: 23/08/20
 * Project: mssc-oil-inventory-service
 * Package: com.crpdev.common.events
 **/
@NoArgsConstructor
public class NewInventoryEvent extends OilEvent {
    public NewInventoryEvent(OilDto oilDto){
        super(oilDto);
    }
}
