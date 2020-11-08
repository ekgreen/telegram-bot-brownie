package com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.login;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginApi {

    private Integer kdResult;

    private String nmResult;

    private String idProfile;

    private Integer cntAuth;

    private String newToken;

    private String session;
}
