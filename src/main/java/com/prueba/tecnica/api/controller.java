//package com.prueba.tecnica.api;
//
//import com.prueba.tecnica.model.Factura;
//import com.prueba.tecnica.repository.FacturaRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//@RestController
//@RequestMapping("/factura")
//@RequiredArgsConstructor
//public class controller {
//
//    private final FacturaRepository facturaRepository;
//
//    @GetMapping("/get")
//    public ResponseEntity<?> getUserProfileImage() {
//        try {
//            return ResponseEntity.ok(facturaRepository.findAll());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
//        }
//    }
//
//    @PostMapping("/post")
//    public ResponseEntity<?> newItem(@RequestParam("codigoF") String codigoF,
//                                     @RequestParam("codigoP") String codigoP,
//                                     @RequestParam("importe") BigDecimal importe,
//                                     @RequestParam("divisa") String divisa,
//                                     @RequestParam("fecha") LocalDate fecha,
//                                     @RequestParam("estado") String estado,
//                                     @RequestParam("extraccionPago") int extraccionPago,
//                                     @RequestParam("iban") String iban) {
//        try {
//            Factura factura = new Factura();
//            factura.setCodigoDeFactura(codigoF);
//            factura.setCodigoDeProveedor(codigoP);
//            factura.setImporte(importe);
//            factura.setDivisa(divisa);
//            factura.setFechaDeVencimiento(fecha);
//            factura.setEstado(estado);
//            factura.setExtraccionPago(extraccionPago);
//            factura.setIban(iban);
//
//
//            return ResponseEntity.ok(facturaRepository.save(factura));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
//        }
//    }
//
//}
