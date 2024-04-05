package com.mms.appNumerosPostgresql.repository;

import com.mms.appNumerosPostgresql.model.Numeros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumerosRespository extends JpaRepository<Numeros, Long> {
}
