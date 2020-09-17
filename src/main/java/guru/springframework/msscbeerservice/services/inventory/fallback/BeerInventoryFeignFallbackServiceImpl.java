package guru.springframework.msscbeerservice.services.inventory.fallback;

import guru.sfg.brewery.model.BeerInventoryDto;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Profile("local-discovery")
@Component
@RequiredArgsConstructor
public class BeerInventoryFeignFallbackServiceImpl implements BeerInventoryFeignService {

    private final BeerInventoryFeignFallbackService beerInventoryFeignFallbackService;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(String beerUpc) {
        return beerInventoryFeignFallbackService.getOnhandInventory();
    }
}
