package com.prueba.tecnica.FacturaProcessor;

import com.prueba.tecnica.model.Factura;
import com.prueba.tecnica.processor.FacturaProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class FacturaProcessorTest {

    private FacturaProcessor facturaProcessor = new FacturaProcessor();

    @Test
    void testProcess() throws Exception {
        Factura factura = new Factura();
        factura.setId(1L);

        Factura result = facturaProcessor.process(factura);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}
