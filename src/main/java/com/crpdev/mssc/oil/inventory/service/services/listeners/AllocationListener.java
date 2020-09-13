package com.crpdev.mssc.oil.inventory.service.services.listeners;

import com.crpdev.factory.oil.model.events.AllocateOrderRequest;
import com.crpdev.factory.oil.model.events.AllocateOrderResult;
import com.crpdev.mssc.oil.inventory.service.config.JmsConfig;
import com.crpdev.mssc.oil.inventory.service.services.AllocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by rajapandian
 * Date: 04/09/20
 * Project: mssc-oil-inventory-service
 * Package: com.crpdev.mssc.oil.inventory.service.services.listeners
 **/
@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequest request){
        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();
        builder.oilOrderDto(request.getOilOrderDto());

        try {
            Boolean allocationResult = allocationService.allocateOrder(request.getOilOrderDto());
            if(allocationResult){
                builder.pendingInventory(false);
            } else {
                builder.pendingInventory(true);
            }
            builder.allocationError(false);
        } catch (Exception e){
            log.debug("Allocation failed for Order Id: " + request.getOilOrderDto().getId());
            builder.allocationError(true);
        } finally {
            jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, builder.build());
        }
    }

}
