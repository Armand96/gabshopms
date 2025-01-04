package com.gaboot.gabshop.api.gateway.common.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class PaginationDto {

    private int page;
    private int dataPerPage;
}
