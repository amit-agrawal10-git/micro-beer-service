package guru.springframework.msscbeerservice.services.listener;

import guru.sfg.brewery.model.ActionResult;
import guru.sfg.brewery.model.BeerOrderDto;
import guru.springframework.msscbeerservice.config.JMSConfig;
import guru.springframework.msscbeerservice.services.order.ValidateOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ValidateOrderService {

    private final ValidateOrder validateOrder;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JMSConfig.VALIDATE_ORDER_REQUEST_QUEUE)
    public void listen(BeerOrderDto beerOrderDto){
        ActionResult actionResult = ActionResult.builder()
                .id(beerOrderDto.getId())
                .isSuccessful(validateOrder.validateOrderRequest(beerOrderDto)).build();

        log.debug("Order Id: "+actionResult.getId()+", Validation Result: "+actionResult.getIsSuccessful());
        jmsTemplate.convertAndSend(JMSConfig.VALIDATE_ORDER_RESPONSE_QUEUE, actionResult);
    }
}
