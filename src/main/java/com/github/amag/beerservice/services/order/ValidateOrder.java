package com.github.amag.beerservice.services.order;

import com.github.amag.beerservice.repositories.BeerRepository;
import com.github.amag.model.BeerOrderDto;
import com.github.amag.model.BeerOrderLineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ValidateOrder {
    private final BeerRepository beerRepository;

    public boolean validateOrderRequest(BeerOrderDto beerOrderDto){
        for (BeerOrderLineDto beerOrderLineDto:beerOrderDto.getBeerOrderLines()) {
            if(beerRepository.findByUpc(beerOrderLineDto.getUpc())==null)
            {
                return false;
            }
        }
        return true;
    }
}
