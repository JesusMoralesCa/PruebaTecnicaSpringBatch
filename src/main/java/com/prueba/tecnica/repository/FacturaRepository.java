package com.prueba.tecnica.repository;

import com.prueba.tecnica.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    List<Factura> findByFechaDeVencimientoAndExtraccionPago(
            @Param("fechaDeVencimiento") LocalDate fechaDeVencimiento,
            @Param("extraccionPago") int extraccionPago
    );
}
