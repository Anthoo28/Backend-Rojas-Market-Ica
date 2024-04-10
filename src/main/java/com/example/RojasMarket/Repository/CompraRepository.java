package com.example.RojasMarket.Repository;

import com.example.RojasMarket.entity.Compra;

public interface CompraRepository {
    public Iterable<Compra> findAll();
    public Compra findById(Long id);
    public Compra save(Compra compra);
    public void delete (Long id);
}
