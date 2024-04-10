package com.example.RojasMarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;


@Entity
public class Promociones {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
   private int id_promociones;
   private String nombre_promocion;
    private String descripcion_promocion;
    private Date fecha_inicio_promocion;
    private Date fecha_fin_promocion;
    private int id_promocion_producto_almacen;


    public Promociones(int id_promociones, String nombre_promocion, String descripcion_promocion, Date fecha_inicio_promocion, Date fecha_fin_promocion, int id_promocion_producto_almacen) {
        this.id_promociones = id_promociones;
        this.nombre_promocion = nombre_promocion;
        this.descripcion_promocion = descripcion_promocion;
        this.fecha_inicio_promocion = fecha_inicio_promocion;
        this.fecha_fin_promocion = fecha_fin_promocion;
        this.id_promocion_producto_almacen = id_promocion_producto_almacen;
    }

    public Promociones() {
    }

    public int getId_promociones() {
        return id_promociones;
    }

    public void setId_promociones(int id_promociones) {
        this.id_promociones = id_promociones;
    }

    public String getNombre_promocion() {
        return nombre_promocion;
    }

    public void setNombre_promocion(String nombre_promocion) {
        this.nombre_promocion = nombre_promocion;
    }

    public String getDescripcion_promocion() {
        return descripcion_promocion;
    }

    public void setDescripcion_promocion(String descripcion_promocion) {
        this.descripcion_promocion = descripcion_promocion;
    }

    public Date getFecha_inicio_promocion() {
        return fecha_inicio_promocion;
    }

    public void setFecha_inicio_promocion(Date fecha_inicio_promocion) {
        this.fecha_inicio_promocion = fecha_inicio_promocion;
    }

    public Date getFecha_fin_promocion() {
        return fecha_fin_promocion;
    }

    public void setFecha_fin_promocion(Date fecha_fin_promocion) {
        this.fecha_fin_promocion = fecha_fin_promocion;
    }

    public int getId_promocion_producto_almacen() {
        return id_promocion_producto_almacen;
    }

    public void setId_promocion_producto_almacen(int id_promocion_producto_almacen) {
        this.id_promocion_producto_almacen = id_promocion_producto_almacen;
    }
}



