package guru.sfg.beer.order.service.web.controllers;

import guru.sfg.beer.order.service.services.BeerOrderService;
import guru.sfg.brewery.model.BeerOrderPagedList;
import guru.sfg.brewery.model.CustomerPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private final BeerOrderService beerOrderService;

    public CustomerController(BeerOrderService beerOrderService) {
        this.beerOrderService = beerOrderService;
    }

    @GetMapping
    public CustomerPagedList listOrders(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize){

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return beerOrderService.listCustomers(PageRequest.of(pageNumber, pageSize));
    }
}
