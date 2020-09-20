package com.github.amag.beerservice.services.inventory;

import com.github.amag.model.BeerInventoryDto;
import com.github.amag.beerservice.config.FeignClientAuthConfig;
import com.github.amag.beerservice.services.inventory.fallback.BeerInventoryFeignFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "beer-inventory-service", fallback = BeerInventoryFeignFallbackServiceImpl.class, configuration = FeignClientAuthConfig.class)
public interface BeerInventoryFeignService {

    @GetMapping(BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(@PathVariable String beerUpc);
}
