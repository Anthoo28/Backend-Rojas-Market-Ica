package com.example.RojasMarket.controller;


import com.example.RojasMarket.Repository.VentaRepository;
import com.example.RojasMarket.entity.Cliente;
import com.example.RojasMarket.entity.Venta;
import com.example.RojasMarket.service.VentaService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/venta")
@CrossOrigin("*")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private  VentaService ventaService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listaventa")
    public Iterable<Venta> Listar(){
        return ventaRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLEADO')")
    @GetMapping(value="/generarPdf/{idVenta}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarPdf(@PathVariable("idVenta") Long idVenta) {
        byte[] data= null;
        data = ventaService.GenerarPdf(idVenta);
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLEADO')")
    @PostMapping("/crearventa")
    public Venta CrearVenta(@RequestBody Venta venta) {
        ventaService.calcularTotalEIGV(venta);
        Venta ventaDev = ventaRepository.save(venta);
        return ventaDev;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminarventa/{id}")
    public void EliminarVenta(@PathVariable Long id) {
        ventaRepository.delete(id);
    }

     //report pdf


}
