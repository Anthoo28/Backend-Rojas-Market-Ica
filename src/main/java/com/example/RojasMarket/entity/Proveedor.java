package com.example.RojasMarket.entity;

import jakarta.persistence.*;

import java.sql.Date;



@Entity
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_proveedor;

    @Column(length = 11)
    private String ruc_proveedor;

    private String nombre_proveedor;

    private String descripcion_proveedor;

    @Column(length = 9)
    private String telefono_proveedor;


    public Proveedor(int id_proveedor, String ruc_proveedor, String nombre_proveedor, String descripcion_proveedor, String telefono_proveedor) {
        this.id_proveedor = id_proveedor;
        this.ruc_proveedor = ruc_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.descripcion_proveedor = descripcion_proveedor;
        this.telefono_proveedor = telefono_proveedor;
    }

    public Proveedor() {
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getRuc_proveedor() {
        return ruc_proveedor;
    }

    public void setRuc_proveedor(String ruc_proveedor) {
        this.ruc_proveedor = ruc_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getDescripcion_proveedor() {
        return descripcion_proveedor;
    }

    public void setDescripcion_proveedor(String descripcion_proveedor) {
        this.descripcion_proveedor = descripcion_proveedor;
    }

    public String getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public void setTelefono_proveedor(String telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }
}
