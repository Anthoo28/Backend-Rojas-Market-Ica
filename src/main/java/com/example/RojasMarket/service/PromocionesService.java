package com.example.RojasMarket.service;

import com.example.RojasMarket.Repository.PromocionesRepository;
import com.example.RojasMarket.entity.Promociones;
import com.example.RojasMarket.entity.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PromocionesService {

    @Autowired
    PromocionesRepository promocionesRepository;

    public List<Promociones> lista(){
        return promocionesRepository.listaPromociones();

    }

    public Optional<Promociones> getById(int id){

        return promocionesRepository.idPromociones(id);
    }

    public void savePromociones(Promociones promociones){
        promocionesRepository.savePromociones(promociones.getNombre_promocion(),promociones.getDescripcion_promocion(),promociones.getFecha_inicio_promocion(),promociones.getFecha_fin_promocion(), promociones.getId_promocion_producto_almacen());
    }

    public void eliminarPromociones(int id){
        promocionesRepository.eliminarpromociones(id);
    }


}
