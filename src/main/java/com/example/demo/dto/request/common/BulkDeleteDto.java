package com.example.demo.dto.request.common;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BulkDeleteDto {
    @NotNull()
    private Long[] ids;
}
