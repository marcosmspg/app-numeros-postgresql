package com.mms.appNumerosPostgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NumerosDTO {
    private Long id;
    private Integer numero;
}
