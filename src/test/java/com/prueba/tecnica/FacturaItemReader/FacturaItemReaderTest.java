package com.prueba.tecnica.FacturaItemReader;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.prueba.tecnica.model.Factura;
import com.prueba.tecnica.reader.FacturaItemReader;
import com.prueba.tecnica.repository.FacturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class FacturaItemReaderTest {

    @Mock
    private FacturaRepository facturaRepository;

    private FacturaItemReader facturaItemReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        facturaItemReader = new FacturaItemReader(facturaRepository, LocalDate.of(2025, 6, 15));
    }

    @Test
    void testRead() throws Exception {
        Factura factura1 = new Factura();
        factura1.setId(1L);
        factura1.setExtraccionPago(0);  // Estado inicial

        Factura factura2 = new Factura();
        factura2.setId(2L);
        factura2.setExtraccionPago(0);  // Estado inicial

        when(facturaRepository.findByFechaDeVencimientoAndExtraccionPago(LocalDate.of(2025, 6, 15), 0))
                .thenReturn(Arrays.asList(factura1, factura2));

        Factura result = facturaItemReader.read();
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(facturaRepository, times(1))
                .findByFechaDeVencimientoAndExtraccionPago(LocalDate.of(2025, 6, 15), 0);

        verify(facturaRepository, times(2)).save(any(Factura.class));
    }
}
