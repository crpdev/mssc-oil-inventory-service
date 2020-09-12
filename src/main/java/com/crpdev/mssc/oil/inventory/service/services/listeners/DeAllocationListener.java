package com.crpdev.mssc.oil.inventory.service.services.listeners;

import com.crpdev.factory.oil.model.events.DeAllocateOrderRequest;
import com.crpdev.mssc.oil.inventory.service.config.JmsConfig;
import com.crpdev.mssc.oil.inventory.service.services.AllocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeAllocationListener {

    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeAllocateOrderRequest request){
        log.debug("Request to De-Allocate for Order Id: " + request.getOilOrderDto().getId());
        allocationService.deAllocateOrder(request.getOilOrderDto());
    }
}
