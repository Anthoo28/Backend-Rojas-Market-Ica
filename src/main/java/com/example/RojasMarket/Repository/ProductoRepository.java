package com.example.RojasMarket.Repository;

import com.example.RojasMarket.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query(value = "call listaProducto()", nativeQuery = true)
    List<Producto> listaProducto();

    @Query(value = "call IDProducto(:idin)", nativeQuery = true)
    Optional<Producto> idProducto(@Param("idin") int idin);


    @Query(value = "call saveProducto(:id_proveedorIn, :nombre_productoIn, :descripcion_productoIn, :cantidad_productoIn, :fecha_vencimientoIn, :precio_ingreso_productoIn, :stock_minimo_productoIn)", nativeQuery = true)
    void saveProducto(
            @Param("id_proveedorIn") int id_proveedorIn,
            @Param("nombre_productoIn") String nombre_productoIn,
            @Param("descripcion_productoIn") String descripcion_productoIn,
            @Param("cantidad_productoIn") int cantidad_productoIn,
            @Param("fecha_vencimientoIn") Date fecha_vencimientoIn,
            @Param("precio_ingreso_productoIn") float precio_ingreso_productoIn,
            @Param("stock_minimo_productoIn") int stock_minimo_productoIn
    );

    @Modifying
    @Query(value = "{call EditProducto(:idIn,:id_proveedorIn, :nombre_productoIn, :descripcion_productoIn, :cantidad_productoIn, :fecha_vencimientoIn, :precio_ingreso_productoIn, :stock_minimo_productoIn)}", nativeQuery = true)
    void updateProducto(
            @Param("idIn") int idIn,
            @Param("id_proveedorIn") int id_proveedorIn,
            @Param("nombre_productoIn") String nombre_productoIn,
            @Param("descripcion_productoIn") String descripcion_productoIn,
            @Param("cantidad_productoIn") int cantidad_productoIn,
            @Param("fecha_vencimientoIn") Date fecha_vencimientoIn,
            @Param("precio_ingreso_productoIn") float precio_ingreso_productoIn,
            @Param("stock_minimo_productoIn") int stock_minimo_productoIn
    );



    @Query(value = "call DeleteProducto(:idin)", nativeQuery = true)
    void eliminarProducto(@Param("idin") int idin);

}
