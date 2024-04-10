package com.example.RojasMarket.Model;

import com.example.RojasMarket.entity.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IVentaDao extends CrudRepository<Venta, Long> {

    @Query(value = "SELECT total FROM venta WHERE id_venta=:idVenta" , nativeQuery = true)
    Double totalByIdVenta(Long idVenta);

    @Query(value = "SELECT igv FROM venta WHERE id_venta=:idVenta" , nativeQuery = true)
    Double montoIgv(Long idVenta);

}
