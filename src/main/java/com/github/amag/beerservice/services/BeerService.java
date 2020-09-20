package com.github.amag.beerservice.services;

import com.github.amag.model.BeerDto;
import com.github.amag.model.BeerPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * Created by jt on 2019-06-06.
 */
public interface BeerService {
    BeerPagedList listBeers(String beerName, String beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);
    BeerDto getByUPC(String upc, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
