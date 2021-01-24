package guru.sfg.beer.order.service.sm.actions;


import guru.sfg.beer.order.service.config.JmsConfig;
import guru.sfg.beer.order.service.domain.BeerOrder;
import guru.sfg.beer.order.service.domain.BeerOrderEventEnum;
import guru.sfg.beer.order.service.domain.BeerOrderStatusEnum;
import guru.sfg.beer.order.service.services.BeerOrderManagerImpl;
import guru.sfg.brewery.model.events.AllocationFailureEvent;
import guru.sfg.brewery.model.events.ValidateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class AllocationFailureAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {


    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        System.out.println("Validate action called!");
        String beerOrderId = (String) stateContext
                .getMessage()
                .getHeaders().getOrDefault(BeerOrderManagerImpl.ORDER_ID_HEADER, " ");

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATION_FAILURE_QUEUE, AllocationFailureEvent.builder()
        .orderId(UUID.fromString(beerOrderId))
                .build());

        log.debug("Sent Allocation Failure message to queue for order id " + beerOrderId);
    }
}
