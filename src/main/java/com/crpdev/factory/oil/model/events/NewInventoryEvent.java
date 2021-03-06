package com.crpdev.factory.oil.model.events;

import com.crpdev.factory.oil.model.OilDto;
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
