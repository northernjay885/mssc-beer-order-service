package guru.sfg.beer.order.service.services.listeners;


import guru.sfg.beer.order.service.config.JmsConfig;
import guru.sfg.beer.order.service.services.BeerOrderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.northernjay.sfg.brewery.model.events.ValidateOrderResult;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidationResultListener {

    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(ValidateOrderResult result) throws InterruptedException {

        log.debug("Validation Result for Order Id: " + result.getOrderId());
        beerOrderManager.sendValidationToStateMachine(result.getIsValid(), result.getOrderId());
    }
}
