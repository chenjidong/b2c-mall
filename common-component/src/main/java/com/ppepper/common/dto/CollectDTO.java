package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class CollectDTO extends SuperDTO {
    private Long accountId;

    private Long collectId;

    private Integer type;

}
