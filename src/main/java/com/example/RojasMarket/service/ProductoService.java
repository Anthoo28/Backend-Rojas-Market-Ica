package com.example.RojasMarket.service;

import com.example.RojasMarket.Repository.ProductoRepository;
import com.example.RojasMarket.entity.Empleado;
import com.example.RojasMarket.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;
    public List<Producto> listaProducto(){
        return productoRepository.listaProducto();
    }
    public Optional<Producto> getById(int id){

        return productoRepository.idProducto(id);
    }

    public void saveProducto(Producto producto){
        productoRepository.saveProducto(producto.getProveedor().getId_proveedor(), producto.getNombre_producto(),producto.getDescripcion_producto(), producto.getCantidad_producto(),producto.getFecha_vencimiento(), producto.getPrecio_ingreso_producto(), producto.getStock_minimo_producto());
    }

    public void updateProducto(Producto producto) {
        productoRepository.updateProducto(
                producto.getId_producto(),
                producto.getProveedor().getId_proveedor(),
                producto.getNombre_producto(),
                producto.getDescripcion_producto(),
                producto.getCantidad_producto(),
                producto.getFecha_vencimiento(),
                producto.getPrecio_ingreso_producto(),
                producto.getStock_minimo_producto()
        );
    }

    public void eliminarProducto(int id){
        productoRepository.eliminarProducto(id);
    }


}
