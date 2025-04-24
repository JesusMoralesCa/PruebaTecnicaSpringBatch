package com.prueba.tecnica.writer;

import com.prueba.tecnica.model.Factura;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class FacturaItemWriter extends FlatFileItemWriter<Factura> {

    public FacturaItemWriter() {
        setResource(new FileSystemResource("facturas_extraidas.csv"));
        setLineAggregator(lineAggregator());
    }

    private LineAggregator<Factura> lineAggregator() {
        DelimitedLineAggregator<Factura> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");
        BeanWrapperFieldExtractor<Factura> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"codigoDeProveedor", "codigoDeFactura", "importe", "divisa", "fechaDeVencimiento", "estado", "extraccionPago", "iban"});
        aggregator.setFieldExtractor(fieldExtractor);
        return aggregator;
    }



}
