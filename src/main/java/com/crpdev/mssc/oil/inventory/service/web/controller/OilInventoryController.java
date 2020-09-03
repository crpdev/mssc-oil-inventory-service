package com.crpdev.mssc.oil.inventory.service.web.controller;

import com.crpdev.factory.oil.model.OilInventoryDto;
import com.crpdev.mssc.oil.inventory.service.repository.OilInventoryRepository;
import com.crpdev.mssc.oil.inventory.service.web.mapper.OilInventoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OilInventoryController {

    private final OilInventoryMapper oilInventoryMapper;
    private final OilInventoryRepository oilInventoryRepository;

    @GetMapping("/api/v1/oil/{oilId}/inventory")
    public List<OilInventoryDto> listOilsById(@PathVariable("oilId") UUID oilId){
        return oilInventoryRepository.findAllByOilId(oilId)
                .stream()
                .map(oilInventoryMapper::oilInventoryToOilInventoryDto)
                .collect(Collectors.toList());
    }

}
