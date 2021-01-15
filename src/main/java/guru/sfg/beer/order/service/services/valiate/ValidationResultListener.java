package guru.sfg.beer.order.service.services.valiate;


import guru.sfg.beer.order.service.config.JmsConfig;
import guru.sfg.beer.order.service.services.BeerOrderManager;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidationResultListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(ValidateOrderResult result) {

        log.debug("Validation Result for Order Id: " + result.getOrderId());
        beerOrderManager.sendValidationToStateMachine(result);
    }
}
