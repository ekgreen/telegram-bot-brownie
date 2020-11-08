package com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.cabinet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class IndicationCounterApi {

    @JsonProperty("nn_days")
    private Integer daysBeforeIndication;

}
