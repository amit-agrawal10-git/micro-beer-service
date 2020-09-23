package com.github.amag.beerservice.services.inventory.fallback;

import com.github.amag.beerservice.services.inventory.BeerInventoryFeignService;
import com.github.amag.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile({"local-discovery","digitalocean"})
@Component
@RequiredArgsConstructor
public class BeerInventoryFeignFallbackServiceImpl implements BeerInventoryFeignService {

    private final BeerInventoryFeignFallbackService beerInventoryFeignFallbackService;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(String beerUpc) {
        return beerInventoryFeignFallbackService.getOnhandInventory();
    }
}
