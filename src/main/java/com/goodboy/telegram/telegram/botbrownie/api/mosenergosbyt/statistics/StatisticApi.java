package com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StatisticApi {

    @JsonProperty("dt_period")
    private LocalDateTime period;

    @JsonProperty("vl_t1")
    private Double t1;

    @JsonProperty("nm_t1")
    private String nameT1;

    @JsonProperty("vl_t2")
    private Double t2;

    @JsonProperty("nm_t2")
    private String nameT2;

    @JsonProperty("vl_t3")
    private Double t3;

    @JsonProperty("nm_t3")
    private String nameT3;

    @JsonProperty("min_vl")
    private Double minValue;

    @JsonProperty("max_vl")
    private Double maxValue;

    @JsonProperty("dt_min")
    private LocalDateTime dateMinValue;

    @JsonProperty("dt_max")
    private LocalDateTime dateMaxValue;

    @JsonProperty("total_vl")
    private Double total;
}
