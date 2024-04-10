package com.example.RojasMarket.service;

import com.example.RojasMarket.Repository.ProveedorRepository;
import com.example.RojasMarket.entity.Empleado;
import com.example.RojasMarket.entity.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    public List<Proveedor> lista(){
        return proveedorRepository.listaProveedor();

    }

    public Optional<Proveedor> getById(int id){

        return proveedorRepository.idProveedor(id);
    }

    public void saveProveedor(Proveedor proveedor){
        proveedorRepository.saveProveedor(proveedor.getRuc_proveedor(),proveedor.getNombre_proveedor(), proveedor.getDescripcion_proveedor(), proveedor.getTelefono_proveedor());
    }

    public void updateProveedor(Proveedor proveedor) {
        proveedorRepository.updateProveedor(
                proveedor.getId_proveedor(),
                proveedor.getRuc_proveedor(),
                proveedor.getNombre_proveedor(),
                proveedor.getDescripcion_proveedor(),
                proveedor.getTelefono_proveedor()
        );
    }

    public void eliminarProveedor(int id){
        proveedorRepository.eliminarProveedor(id);
    }

}
