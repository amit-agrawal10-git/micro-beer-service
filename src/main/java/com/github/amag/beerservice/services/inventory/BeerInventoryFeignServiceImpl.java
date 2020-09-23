package com.github.amag.beerservice.services.inventory;

import com.github.amag.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Profile({"local-discovery","digitalocean"})
@Slf4j
@Component
@RequiredArgsConstructor
public class BeerInventoryFeignServiceImpl implements BeerInventoryService {
    private final BeerInventoryFeignService beerInventoryFeignService;

    @Override
    public Integer getOnhandInventory(String beerUpc) {
        log.debug("Calling Inventory Service");

        ResponseEntity<List<BeerInventoryDto>> responseEntity =
                beerInventoryFeignService.getOnhandInventory(beerUpc);

        //sum from inventory list
        Integer onHand = Objects
                .requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }
}
