package com.prueba.tecnica.reader;

import com.prueba.tecnica.model.Factura;
import com.prueba.tecnica.repository.FacturaRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.core.configuration.annotation.StepScope;

import java.time.LocalDate;
import java.util.List;

@StepScope
public class FacturaItemReader implements ItemReader<Factura> {

    private final FacturaRepository facturaRepository;
    private List<Factura> facturas;
    private int currentIndex = 0;
    private LocalDate fecha;

    public FacturaItemReader(FacturaRepository facturaRepository, LocalDate fecha) {
        this.facturaRepository = facturaRepository;
        this.fecha = fecha;
    }

    @Override
    public Factura read() throws Exception {
        if (facturas == null) {
            // Filtra las facturas basándose en la fecha
            System.out.println("Cargando facturas con fecha: " + fecha); // Log para verificar la fecha
            facturas = facturaRepository.findByFechaDeVencimientoAndExtraccionPago(fecha, 0);
            System.out.println("Número de facturas cargadas: " + facturas.size());
            FacturaItemUpdater(facturas);
        }

        if (currentIndex < facturas.size()) {
            return facturas.get(currentIndex++);
        }
        return null; // No hay más elementos
    }

    public void FacturaItemUpdater(List<Factura> facturas){
        for (Factura factura : facturas) {
            // Aquí actualizas las facturas en la base de datos
            factura.setExtraccionPago(1);
            facturaRepository.save(factura);
            System.out.println("Actualizada factura con ID " + factura.getId() + ", extraccionPago: " + factura.getExtraccionPago());
        }
    }
}
