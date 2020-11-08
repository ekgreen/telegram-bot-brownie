package com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentRedirectApi {

    private String kdResult;

    private String nmResult;

    @JsonProperty("nm_pay_link")
    private String redirect;
}
