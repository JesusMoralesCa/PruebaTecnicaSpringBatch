package com.prueba.tecnica.processor;

import com.prueba.tecnica.model.Factura;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FacturaProcessor implements ItemProcessor<Factura, Factura> {

    @Override
    public Factura process(Factura factura) throws Exception {
        System.out.println("Procesando factura: " + factura);
        return factura;
    }
}
