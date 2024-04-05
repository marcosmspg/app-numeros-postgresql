package com.mms.appNumerosPostgresql;

import com.mms.appNumerosPostgresql.controller.NumerosController;
import com.mms.appNumerosPostgresql.service.NumerosService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NumerosTest {

    @Mock
    static NumerosService numerosService;

    @InjectMocks
    static NumerosController numerosController;

    @BeforeAll
    static void carga(){
        numerosController = new NumerosController(numerosService);
    }

    @Test
    void testOrdenarListaNumerosCasoPropuesto() {
        // [1, 15, 5, 7, 3] = [0001, 1111, 0101, 0111, 0011]
        // [15, 7, 3, 5, 1]

        // Arrange
        List<Integer> listaDesordenada  = List.of(1, 15, 5, 7, 3);
        List<Integer> listaOrdenada     = List.of(15, 7, 3, 5, 1);
        // Act
        List<Integer> listaRecuperada = numerosController.ordenarListaNumeros(listaDesordenada);
        // Assert
        assertIterableEquals(listaRecuperada, listaOrdenada);
    }

    @Test
    void testOrdenarListaNumerosAleatorios() throws Exception {
        // [6, 8, 7, 9, 2]  = [0110, 1000, 0111, 1001, 0010]
        // [7, 6, 9, 2, 8]

        // Arrange
        List<Integer> listaDesordenada  = List.of(6, 8, 7, 9, 2);
        List<Integer> listaOrdenada     = List.of(7, 6, 9, 2, 8);
        // Act
        List<Integer> listaRecuperada = numerosController.ordenarListaNumeros(listaDesordenada);

        // Assert
        assertIterableEquals(listaRecuperada, listaOrdenada);
    }

    @Test
    void testOrdenarListaNumerosSecuencia() throws Exception {
        // [1, 2, 3, 4, 5]  = [0001, 0010, 0011, 0100, 0101]
        // [3, 5, 1, 2, 4]

        // Arrange
        List<Integer> listaDesordenada  = List.of(1, 2, 3, 4, 5);
        List<Integer> listaOrdenada     = List.of(3, 5, 1, 2, 4);
        // Act
        List<Integer> listaRecuperada = numerosController.ordenarListaNumeros(listaDesordenada);
        // Assert
        assertIterableEquals(listaRecuperada, listaOrdenada);
    }

    @Test
    void testOrdenarListaNumerosPares() throws Exception {
        // [2, 4, 6, 8, 10] = [0010, 0100, 0110, 1000, 1010]
        // [6, 10, 2, 4, 8]

        // Arrange
        List<Integer> listaDesordenada  = List.of(2, 4, 6, 8, 10);
        List<Integer> listaOrdenada     = List.of(6, 10, 2, 4, 8);
        // Act
        List<Integer> listaRecuperada = numerosController.ordenarListaNumeros(listaDesordenada);
        // Assert
        assertIterableEquals(listaRecuperada, listaOrdenada);
    }

    @Test
    void testOrdenarListaNumerosImpares() throws Exception {
        // [1, 3, 5, 7, 9] = [0001, 0011, 0101, 0111, 1001]
        // [7, 3, 5, 9, 1]

        // Arrange
        List<Integer> listaDesordenada  = List.of(1, 3, 5, 7, 9);
        List<Integer> listaOrdenada     = List.of(7, 3, 5, 9, 1);
        // Act
        List<Integer> listaRecuperada = numerosController.ordenarListaNumeros(listaDesordenada);

        // Assert
        assertIterableEquals(listaRecuperada, listaOrdenada);
    }

    @Test
    void testOrdenarListaNumerosNullDevuelveArrayVacio() throws Exception {
        // Arrange
        List<Integer> listaDesordenada  = null;
        // Act
        List<Integer> listaRecuperada = numerosController.ordenarListaNumeros(listaDesordenada);
        // Assert
        assertTrue(listaRecuperada.isEmpty());
    }

}