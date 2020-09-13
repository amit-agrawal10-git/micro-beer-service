package guru.springframework.msscbeerservice.services.brewing;

import guru.sfg.beer.common.BeerDto;
import guru.springframework.msscbeerservice.config.JMSConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.sfg.beer.common.events.BrewBeerEvent;
import guru.sfg.beer.common.events.NewInventoryEvent;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JMSConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent brewBeerEvent){
        BeerDto beerDto = brewBeerEvent.getBeerDto();
        Beer beer = beerRepository.getOne(beerDto.getId());
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());
        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer :"+beer.getMinOnHand()+", QoH: "+beer.getQuantityToBrew());
        jmsTemplate.convertAndSend(JMSConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
