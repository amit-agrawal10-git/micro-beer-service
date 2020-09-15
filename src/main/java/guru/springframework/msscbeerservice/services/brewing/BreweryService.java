package guru.springframework.msscbeerservice.services.brewing;

import guru.springframework.msscbeerservice.config.JMSConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
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

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(
                beer -> {
                    Integer invQoH = beerInventoryService.getOnhandInventory(beer.getId());
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
