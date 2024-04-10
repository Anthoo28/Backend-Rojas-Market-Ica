package com.example.RojasMarket.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;


    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;
    private String nombre_producto;
    private String descripcion_producto;

    private Date fecha_vencimiento;
    private int cantidad_producto;

    private float precio_ingreso_producto;
    private float precio_salida_producto;
     private int stock_minimo_producto;



}
