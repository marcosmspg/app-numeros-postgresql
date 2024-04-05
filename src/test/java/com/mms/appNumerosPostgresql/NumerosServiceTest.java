package com.mms.appNumerosPostgresql;

import com.mms.appNumerosPostgresql.dto.NumerosDTO;
import com.mms.appNumerosPostgresql.mapper.NumerosMapper;
import com.mms.appNumerosPostgresql.model.Numeros;
import com.mms.appNumerosPostgresql.repository.NumerosRespository;
import com.mms.appNumerosPostgresql.service.NumerosService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NumerosServiceTest {

    @Mock
    NumerosRespository numerosRespositoryMock;

    @Mock
    private NumerosMapper numerosMapper = Mappers.getMapper(NumerosMapper.class);

    @InjectMocks
    NumerosService numerosService;

    @BeforeAll
    static void carga(){
        //numerosController = new NumerosController(numerosService);
    }

    @DisplayName("Los números del Respository son los esperados por el servicio")
    @Test
    void comprobar_numeros_respository_igual_numeros_esperados_servicio() {

        // Arrange - [1, 15, 5, 7, 3]
        Numeros num1 = Numeros.builder().id(1L).numero(1).build();
        Numeros num2 = Numeros.builder().id(2L).numero(15).build();
        Numeros num3 = Numeros.builder().id(3L).numero(5).build();
        Numeros num4 = Numeros.builder().id(4L).numero(7).build();
        Numeros num5 = Numeros.builder().id(5L).numero(3).build();

        NumerosDTO numDto1 = NumerosDTO.builder().id(1L).numero(1).build();
        NumerosDTO numDto2 = NumerosDTO.builder().id(2L).numero(15).build();
        NumerosDTO numDto3 = NumerosDTO.builder().id(3L).numero(5).build();
        NumerosDTO numDto4 = NumerosDTO.builder().id(4L).numero(7).build();
        NumerosDTO numDto5 = NumerosDTO.builder().id(5L).numero(3).build();

        when(numerosMapper.toNumerosDTO(num1)).thenReturn(numDto1);
        when(numerosMapper.toNumerosDTO(num2)).thenReturn(numDto2);
        when(numerosMapper.toNumerosDTO(num3)).thenReturn(numDto3);
        when(numerosMapper.toNumerosDTO(num4)).thenReturn(numDto4);
        when(numerosMapper.toNumerosDTO(num5)).thenReturn(numDto5);

        when(numerosRespositoryMock.findAll()).thenReturn(List.of(num1, num2, num3, num4, num5));

        // Act
        List<NumerosDTO> listaNumeros = numerosService.getListaNumeros();

        // Asssert
        verify(numerosRespositoryMock, times(1)).findAll();

        Assertions.assertThat(listaNumeros).isNotNull();

        // Los objetos tienen el valor entero esperado
        assertEquals(listaNumeros.get(0).getNumero().intValue(), 1);
        assertEquals(listaNumeros.get(1).getNumero().intValue(), 15);
        assertEquals(listaNumeros.get(2).getNumero().intValue(), 5);
        assertEquals(listaNumeros.get(3).getNumero().intValue(), 7);
        assertEquals(listaNumeros.get(4).getNumero().intValue(), 3);

        // Los objetos de la capa servicio tienen el mismo valor que la capa repository
        Assertions.assertThat(listaNumeros.get(0).getNumero()).isEqualTo(num1.getNumero());
        Assertions.assertThat(listaNumeros.get(1).getNumero()).isEqualTo(num2.getNumero());
        Assertions.assertThat(listaNumeros.get(2).getNumero()).isEqualTo(num3.getNumero());
        Assertions.assertThat(listaNumeros.get(3).getNumero()).isEqualTo(num4.getNumero());
        Assertions.assertThat(listaNumeros.get(4).getNumero()).isEqualTo(num5.getNumero());
    }
}