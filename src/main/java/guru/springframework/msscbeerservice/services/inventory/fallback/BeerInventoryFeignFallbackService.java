package guru.springframework.msscbeerservice.services.inventory.fallback;

import guru.sfg.brewery.model.BeerInventoryDto;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryServiceRestTemplateImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "inventory-failover")
public interface BeerInventoryFeignFallbackService {

    @GetMapping("/inventory-failover")
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory();
}
