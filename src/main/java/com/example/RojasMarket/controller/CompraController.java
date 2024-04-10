package com.example.RojasMarket.controller;

import com.example.RojasMarket.Repository.CompraRepository;
import com.example.RojasMarket.entity.Compra;
import com.example.RojasMarket.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
@CrossOrigin("*")
public class CompraController {
    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CompraService compraService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listaCompra")
    public Iterable<Compra> Listar(){
        return compraRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLEADO')")
    @PostMapping("/crearCompra")
    public Compra CrearCompra(@RequestBody Compra compra) {
        compraService.calcularTotalEIGV1(compra);
        Compra compraDev = compraRepository.save(compra);
        return compraDev;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminarCompra/{id}")
    public void EliminarCompra(@PathVariable Long id) {
        compraRepository.delete(id);
    }

}
