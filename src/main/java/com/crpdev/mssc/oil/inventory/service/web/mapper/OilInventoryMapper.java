package com.crpdev.mssc.oil.inventory.service.web.mapper;

import com.crpdev.factory.oil.model.OilInventoryDto;
import com.crpdev.mssc.oil.inventory.service.domain.OilInventory;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface OilInventoryMapper {

    public OilInventoryDto oilInventoryToOilInventoryDto(OilInventory oilInventory);

    public OilInventory OilInventoryDtoToOilInventory(OilInventoryDto oilInventoryDto);
}
