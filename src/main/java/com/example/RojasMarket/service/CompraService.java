package com.example.RojasMarket.service;

import com.example.RojasMarket.Model.ICompraDao;
import com.example.RojasMarket.Repository.CompraRepository;
import com.example.RojasMarket.Repository.ProductoRepository;
import com.example.RojasMarket.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompraService  implements CompraRepository {

    @Autowired
    private ICompraDao compraDao;

    @Autowired
    private ProductoRepository productoRepository;
    @Transactional
    public void calcularTotalEIGV1(Compra compra) {
        double total = 0.0;
        for (DetalleCompra detalle : compra.getItems1()) {
            long productoId = detalle.getProducto().getId_producto();
            int cantidad = detalle.getCantidad();

            Integer productoIdInteger = Math.toIntExact(productoId);

            Producto producto = productoRepository.findById(productoIdInteger).orElse(null);
            if (producto != null) {
                float precioSalida = producto.getPrecio_salida_producto();
                float subtotal = precioSalida * cantidad;
                total += subtotal;

                int cantidadActual = producto.getCantidad_producto();
                producto.setCantidad_producto(cantidadActual + cantidad);

                float precioIngreso =detalle.getPrecio_unitario();
                producto.setPrecio_ingreso_producto(precioIngreso);

                productoRepository.save(producto);
            }
        }

        double igv = total * 0.18;

        compra.setIgv(igv);
        compra.setTotal(total + igv);
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<Compra> findAll() {
        return compraDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Compra findById(Long id) {
        return compraDao.findById(id).orElse(null);
    }



    @Override
    @Transactional
    public Compra save(Compra compra) {
        Compra savedCompra = compraDao.save(compra);

        return savedCompra;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        compraDao.deleteById(id);
    }

}
