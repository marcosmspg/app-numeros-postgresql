package com.mms.appNumerosPostgresql.service;

import com.mms.appNumerosPostgresql.dto.NumerosDTO;
import com.mms.appNumerosPostgresql.mapper.NumerosMapper;
import com.mms.appNumerosPostgresql.repository.NumerosRespository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NumerosService {

    private NumerosRespository numerosRespository;
    private NumerosMapper numerosMapper;

    public NumerosService(NumerosRespository numerosRespository, NumerosMapper numerosMapper){
        this.numerosRespository = numerosRespository;
        this.numerosMapper = numerosMapper;
    }

    public List<NumerosDTO> getListaNumeros(){
        return numerosRespository.findAll()
                .stream()
                .map(numerosMapper::toNumerosDTO)
                .toList();
    }

}