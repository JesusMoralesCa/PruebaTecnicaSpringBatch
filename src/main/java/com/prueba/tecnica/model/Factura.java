package com.prueba.tecnica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "FACTURAS")
public class Factura {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "factura_seq")
    @SequenceGenerator(name = "factura_seq", sequenceName = "factura_seq", allocationSize = 1)
    private Long id;

    @Column(name = "CODIGO_DE_FACTURA")
    private String codigoDeFactura;

    @Column(name = "CODIGO_DE_PROVEEDOR")
    private String codigoDeProveedor;

    @Column(name = "IMPORTE")
    private BigDecimal importe;

    @Column(name = "DIVISA")
    private String divisa;

    @Column(name = "FECHA_DE_VENCIMIENTO")
    private LocalDate fechaDeVencimiento;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "EXTRACCION_PAGO")
    private int extraccionPago;

    @Column(name = "IBAN")
    private String iban;
}