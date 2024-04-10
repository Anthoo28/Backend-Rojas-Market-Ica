package com.example.RojasMarket.Repository;

import com.example.RojasMarket.entity.Promociones;
import com.example.RojasMarket.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface PromocionesRepository extends JpaRepository<Promociones, Integer> {


    @Query(value = "call listaPromociones()", nativeQuery = true)
    List<Promociones> listaPromociones();


    @Query(value = "call IDPromociones(:idin)", nativeQuery = true)
    Optional<Promociones> idPromociones(@Param("idin") int idin);


    @Query(value = "call savePromociones(:nombre_promocionIn, :descripcion_promocionIn, :fecha_inicio_promocionIn, :fecha_fin_promocionIn, :id_promocion_producto_almacenIn)", nativeQuery = true)
    void savePromociones(
            @Param("nombre_promocionIn") String nombre_promocionIn,
            @Param("descripcion_promocionIn") String descripcion_promocionIn,
            @Param("fecha_inicio_promocionIn") Date fecha_inicio_promocionIn,
            @Param("fecha_fin_promocionIn") Date fecha_fin_promocionIn,
            @Param("id_promocion_producto_almacenIn") int id_promocion_producto_almacenIn
    );

    @Query(value = "call DeletePromociones(:idIn)", nativeQuery = true)
    void eliminarpromociones(@Param("idIn") int idIN);
}



