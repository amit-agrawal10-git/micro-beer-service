package com.github.amag.beerservice.services.inventory;

import java.util.UUID;

public interface BeerInventoryService {
    Integer getOnhandInventory(String beerUpc);
}
