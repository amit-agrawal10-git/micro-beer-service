package guru.springframework.msscbeerservice.services.inventory;

import guru.sfg.brewery.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "beer-inventory-service")
public interface BeerInventoryFeignService {

    @GetMapping(BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(@PathVariable String beerUpc);
}
