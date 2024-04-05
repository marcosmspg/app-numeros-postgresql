package com.mms.appNumerosPostgresql.controller;

import com.mms.appNumerosPostgresql.dto.NumerosDTO;
import com.mms.appNumerosPostgresql.service.NumerosService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NumerosController {

    private NumerosService numerosService;

    public NumerosController(NumerosService numerosService){
        this.numerosService = numerosService;
    }

    @GetMapping("/ordenar-num-bbdd")
    public ResponseEntity<?> ordenarNumerosBBDD(){
        return ResponseEntity.ok(
                ordenarListaNumeros(
                        numerosService.getListaNumeros().stream()
                                .map(NumerosDTO::getNumero)
                                .toList()
                )
        );
    }

    //POST: http://localhost:8080/ordenar
    //BODY REQUEST: [1, 15, 5, 7, 3]
    @PostMapping(value = "/ordenar", consumes = MediaType.APPLICATION_JSON_VALUE,
                                     produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Integer>> ordenarListaDefinitiva(@RequestBody List<Integer> numeros) {
        if (numeros == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok( ordenarListaNumeros(numeros) );
    }

    /**
     * Se recibe una lista de Integers y retorna una lista con las cifras
     * ordenadas según el número de unos de su representación binaria. En caso de
     * empate, el aparecerá antes.
     *
     * @param numeros = lista de números a ordenar
     * @return List<Integer> = lita de números ordenados
     */
    public List<Integer> ordenarListaNumeros(List<Integer> numeros){

        if (numeros == null){
            return new ArrayList<Integer>();
        }

        List<Integer> numerosMutable = new ArrayList<>(numeros);

        numerosMutable.sort((num1, num2) -> {
            int chars1 = (int) Integer.toBinaryString(num1).chars().filter(c -> c == '1').count();
            int chars2 = (int) Integer.toBinaryString(num2).chars().filter(c -> c == '1').count();

            if (chars1 != chars2) {     // comparación binaria caracteres
                return chars2 - chars1;
            } else {                    // comparación decimal
                return num1 - num2;
            }
        });

        return numerosMutable;
    }
}