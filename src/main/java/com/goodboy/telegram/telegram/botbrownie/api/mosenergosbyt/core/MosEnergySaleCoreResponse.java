package com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.core;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MosEnergySaleCoreResponse<T> {

    private boolean success;

    private Long total;

    private T data;

    private MosEnergySaleMetaData metaData;
}
