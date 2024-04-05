package com.mms.appNumerosPostgresql.mapper;

import com.mms.appNumerosPostgresql.dto.NumerosDTO;
import com.mms.appNumerosPostgresql.model.Numeros;
import org.mapstruct.Mapper;

@Mapper
public interface NumerosMapper {
    Numeros toNumeros(NumerosDTO numerosDTO);
    NumerosDTO toNumerosDTO(Numeros numeros);
}
