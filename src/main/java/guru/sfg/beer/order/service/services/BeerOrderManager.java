package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.domain.BeerOrder;
import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.events.ValidateOrderResult;

import java.util.UUID;


public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);

    void sendValidationToStateMachine(Boolean isValid, UUID orderId) throws InterruptedException;

    void beerOrderAllocationPassed(BeerOrderDto beerOrderDto);

    void beerOrderAllocationPendingInventory(BeerOrderDto beerOrderDto);

    void beerOrderAllocationFailed(BeerOrderDto beerOrderDto);

    void beerOrderPickedUp(UUID id);

    void beerOrderCancel(UUID id);

}
