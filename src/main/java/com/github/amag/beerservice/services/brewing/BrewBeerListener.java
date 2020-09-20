package com.github.amag.beerservice.services.brewing;

import com.github.amag.beerservice.repositories.BeerRepository;
import com.github.amag.model.BeerDto;
import com.github.amag.beerservice.config.JMSConfig;
import com.github.amag.beerservice.domain.Beer;
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
    public void listen(BeerDto beerDto){

        Beer beer = beerRepository.findByUpc(beerDto.getUpc());
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        log.debug("Brewed beer :"+beer.getMinOnHand()+", QoH: "+beer.getQuantityToBrew());
        jmsTemplate.convertAndSend(JMSConfig.NEW_INVENTORY_QUEUE, beerDto);
    }
}
