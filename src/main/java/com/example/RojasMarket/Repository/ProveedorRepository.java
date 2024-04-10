package com.example.RojasMarket.Repository;


import com.example.RojasMarket.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {



@Query(value = "call listaProveedor()", nativeQuery = true)
List<Proveedor> listaProveedor();


@Query(value = "call IDProveedor(:idin)", nativeQuery = true)
Optional<Proveedor> idProveedor(@Param("idin") int idin);


@Query(value = "call saveProveedor(:ruc_proveedorIn,:nombre_proveedorIn,:descripcion_proveedorIn,:telefono_proveedorIn)", nativeQuery = true)
void saveProveedor(
        @Param("ruc_proveedorIn") String ruc_proveedorIn,
        @Param("nombre_proveedorIn") String nombre_proveedorIn,
        @Param("descripcion_proveedorIn") String descripcion_proveedorIn,
        @Param("telefono_proveedorIn") String telefono_proveedorIn
);

    @Modifying
    @Query(value = "{call EditProveedor(:idIn, :ruc_proveedorIn, :nombre_proveedorIn, :descripcion_proveedorIn, :telefono_proveedorIn)}", nativeQuery = true)
    void updateProveedor(
            @Param("idIn") int idIn,
            @Param("ruc_proveedorIn") String ruc_proveedorIn,
            @Param("nombre_proveedorIn") String nombre_proveedorIn,
            @Param("descripcion_proveedorIn") String descripcion_proveedorIn,
            @Param("telefono_proveedorIn") String telefono_proveedorIn
    );


    @Query(value = "call DeleteProveedor(:idIn)", nativeQuery = true)
void eliminarProveedor(@Param("idIn") int idIN);
}
