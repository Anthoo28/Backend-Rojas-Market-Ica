package com.example.RojasMarket.controller;


import com.example.RojasMarket.entity.Empleado;
import com.example.RojasMarket.entity.Proveedor;
import com.example.RojasMarket.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin("*")
public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<Proveedor>> lista(){
        List<Proveedor> lista = proveedorService.lista();
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/verid/{id}")
    public ResponseEntity<Proveedor> verId(@PathVariable("id") int id){
        Optional<Proveedor> proveedor = proveedorService.getById(id);
        return new ResponseEntity(proveedor, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Proveedor proveedor) {
        proveedorService.saveProveedor(proveedor);
        return new ResponseEntity("producto guardado con exito", HttpStatus.CREATED);}

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Proveedor proveedor) {
        Optional<Proveedor> existingProveedor = proveedorService.getById(id);
        if (existingProveedor.isPresent()) {
            Proveedor updateProveedor = existingProveedor.get();
            updateProveedor.setRuc_proveedor(proveedor.getRuc_proveedor());
            updateProveedor.setNombre_proveedor(proveedor.getNombre_proveedor());
            updateProveedor.setDescripcion_proveedor(proveedor.getDescripcion_proveedor());
            updateProveedor.setTelefono_proveedor(proveedor.getTelefono_proveedor());
            proveedorService.updateProveedor(updateProveedor);
            return ResponseEntity.ok("Proveedor actualizado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/eliminar/{id}")
        public ResponseEntity<?> eliminar ( @PathVariable("id") int id){
            proveedorService.eliminarProveedor(id);
            return new ResponseEntity("proveedor eliminado", HttpStatus.OK);
        }
    }
