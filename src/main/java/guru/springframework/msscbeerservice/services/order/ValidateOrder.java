package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.BeerOrderLineDto;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
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
