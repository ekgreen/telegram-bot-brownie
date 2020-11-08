package com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.cabinet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CurrentBalanceApi {

    @JsonProperty("vl_balance")
    private Double balance;
}
