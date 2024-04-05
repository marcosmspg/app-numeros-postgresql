package com.mms.appNumerosPostgresql;

import com.mms.appNumerosPostgresql.controller.NumerosController;
import com.mms.appNumerosPostgresql.service.NumerosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(NumerosController.class)
@ExtendWith(MockitoExtension.class)
class NumerosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NumerosService numerosServiceMock;

    @Test
    void testOrdenarListaNumerosCasoPropuesto() throws Exception {
        // [1, 15, 5, 7, 3] = [0001, 1111, 0101, 0111, 0011]
        // [15, 7, 3, 5, 1]
        mockMvc.perform(post("/ordenar")
                        .content("[1,15,5,7,3]")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) )
                .andExpect(status().isOk())
                .andExpect( content().string("[15,7,3,5,1]"));
    }

    @Test
    void testOrdenarListaNumerosAleatorios() throws Exception {
        // [6, 8, 7, 9, 2]  = [0110, 1000, 0111, 1001, 0010]
        // [7, 6, 9, 2, 8]
        mockMvc.perform(post("/ordenar")
                        .content("[6,8,7,9,2]")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) )
                .andExpect(status().isOk())
                .andExpect( content().string("[7,6,9,2,8]"));
    }

    @Test
    void testOrdenarListaNumerosSecuencia() throws Exception {
        // [1, 2, 3, 4, 5]  = [0001, 0010, 0011, 0100, 0101]
        // [3, 5, 1, 2, 4]
        mockMvc.perform(post("/ordenar")
                        .content("[1,2,3,4,5]")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) )
                .andExpect(status().isOk())
                .andExpect( content().string("[3,5,1,2,4]"));
    }

    @Test
    void testOrdenarListaNumerosPares() throws Exception {
        // [2, 4, 6, 8, 10] = [0010, 0100, 0110, 1000, 1010]
        // [6, 10, 2, 4, 8]
        mockMvc.perform(post("/ordenar")
                        .content("[2,4,6,8,10]")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) )
                .andExpect(status().isOk())
                .andExpect( content().string("[6,10,2,4,8]"));
    }

    @Test
    void testOrdenarListaNumerosImpares() throws Exception {
        // [1, 3, 5, 7, 9] = [0001, 0011, 0101, 0111, 1001]
        // [7, 3, 5, 9, 1]
        mockMvc.perform(post("/ordenar")
                        .content("[1,3,5,7,9]")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) )
                .andExpect(status().isOk())
                .andExpect( content().string("[7,3,5,9,1]"));
    }
    @Test
    void testOrdenarListaNumerosNullBadRequest() throws Exception {
        // [1, 3, 5, 7, 9] = [0001, 0011, 0101, 0111, 1001]
        // [7, 3, 5, 9, 1]
        mockMvc.perform(post("/ordenar")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testOrdenarListaNumerosVaciaBadRequest() throws Exception {
        // [1, 3, 5, 7, 9] = [0001, 0011, 0101, 0111, 1001]
        // [7, 3, 5, 9, 1]
        mockMvc.perform(post("/ordenar")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testOrdenarListaNumerosConUnaComaAlFinalBadRequest() throws Exception {
        // [1,3,5,7,]
        mockMvc.perform(post("/ordenar")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("[1,3,5,7,]"))
                .andExpect(status().isBadRequest());
    }
}