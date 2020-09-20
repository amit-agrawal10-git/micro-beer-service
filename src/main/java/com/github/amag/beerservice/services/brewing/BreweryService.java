package com.github.amag.beerservice.services.brewing;

import com.github.amag.beerservice.repositories.BeerRepository;
import com.github.amag.beerservice.config.JMSConfig;
import com.github.amag.beerservice.domain.Beer;
import com.github.amag.beerservice.services.inventory.BeerInventoryService;
import com.github.amag.beerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BreweryService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 50000)
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(
                beer -> {
                    Integer invQoH = beerInventoryService.getOnhandInventory(beer.getUpc());
                    log.debug("Min onhand is: "+beer.getMinOnHand());
                    log.debug("Inventory on hand is: "+invQoH);

                    if(invQoH<=beer
                    .getMinOnHand()){
                        jmsTemplate.convertAndSend(JMSConfig.BREWING_REQUEST_QUEUE,beerMapper.beerToBeerDto(beer));
                    }
                }
        );
    }

}
