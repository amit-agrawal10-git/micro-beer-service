package guru.sfg.beer.common.events;

import guru.sfg.beer.common.BeerDto;
import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -895501866408672271L;
    public BeerDto beerDto;
}
