package com.example.RojasMarket.Repository;

import com.example.RojasMarket.entity.Venta;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.Optional;


public interface VentaRepository {

    public Iterable<Venta> findAll();
    public Venta findById(Long id);
    public Venta save(Venta venta);
    public void delete (Long id);
    public byte[] GenerarPdf(Long id) ;

}


