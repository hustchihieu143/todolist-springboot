package com.example.demo.dto.request.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaginationDto {
    @JsonProperty("page")
    private Long page;

    @JsonProperty("perPage")
    private Long perPage;
}
