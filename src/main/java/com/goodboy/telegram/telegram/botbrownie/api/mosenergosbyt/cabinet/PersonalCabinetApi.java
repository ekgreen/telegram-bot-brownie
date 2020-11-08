package com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.cabinet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PersonalCabinetApi {

    @JsonProperty("nn_ls")
    private String account;

    @JsonProperty("nm_ls_group_full")
    private String address;

    @JsonProperty("nm_ls_group")
    private String partAddress;

    @JsonProperty("nm_type")
    private String service;

    @JsonProperty("nm_provider")
    private String serviceProvider;

    private Integer kdProvider;

    private String vlProvider;

    private Long idService;

    private PersonalCabinetDataApi data;

    @JsonProperty("pr_ls_group_edit")
    private Boolean editCabinet;

    @JsonProperty("nm_lock_msg")
    private String lockMessage;

    @JsonProperty("kd_status")
    private Integer cabinetStatus;

    private Integer kdServiceType;

    @JsonProperty("nm_ls_description")
    private String description;

    @Data
    @Accessors(chain = true)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class PersonalCabinetDataApi{

        Integer kdReg;

        @JsonProperty("NN_SNILS")
        String snils;

        @JsonProperty("nm_street")
        String street;

        @JsonProperty("nn_ls_disp")
        String account;

        @JsonProperty("KD_LS_OWNER_TYPE")
        Integer cabinetOwnerType;
    }
}
