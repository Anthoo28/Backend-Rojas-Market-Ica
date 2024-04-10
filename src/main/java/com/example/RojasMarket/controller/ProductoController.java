package com.example.RojasMarket.controller;


import com.example.RojasMarket.entity.Empleado;
import com.example.RojasMarket.entity.Producto;
import com.example.RojasMarket.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Producto>> lista(){
        List<Producto> lista = productoService.listaProducto();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @GetMapping("/verid/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<com.example.RojasMarket.entity.Producto> verId(@PathVariable("id") int id){
        Optional<com.example.RojasMarket.entity.Producto> producto = productoService.getById(id);
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody com.example.RojasMarket.entity.Producto producto) {
        productoService.saveProducto(producto);
        return new ResponseEntity("producto guardado con exito", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Producto producto) {
        Optional<Producto> existingProducto = productoService.getById(id);
        if (existingProducto.isPresent()) {
            Producto updateProducto = existingProducto.get();
            updateProducto.setProveedor(producto.getProveedor());
            updateProducto.setNombre_producto(producto.getNombre_producto());
            updateProducto.setDescripcion_producto(producto.getDescripcion_producto());
            updateProducto.setCantidad_producto(producto.getCantidad_producto());
            updateProducto.setFecha_vencimiento(producto.getFecha_vencimiento());
            updateProducto.setPrecio_ingreso_producto(producto.getPrecio_ingreso_producto());
            updateProducto.setStock_minimo_producto(producto.getStock_minimo_producto());
            productoService.updateProducto(updateProducto);
            return ResponseEntity.ok("Producto actualizado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public  ResponseEntity<?> eliminar(@PathVariable("id") int id){
        productoService.eliminarProducto(id);
        return new ResponseEntity("producto  eliminado", HttpStatus.OK);
    }

}
