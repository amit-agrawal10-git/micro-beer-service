package com.github.amag.beerservice.services.inventory.fallback;

import com.github.amag.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "inventory-failover")
public interface BeerInventoryFeignFallbackService {

    @GetMapping("/inventory-failover")
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory();
}
