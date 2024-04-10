package com.example.RojasMarket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity
@Table(name = "detalleventa")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_dventa;


    private Integer cantidad;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public DetalleVenta(Long id_dventa, Integer cantidad, Producto producto) {
        this.id_dventa = id_dventa;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public DetalleVenta() {
    }

    public Long getId_dventa() {
        return id_dventa;
    }

    public void setId_dventa(Long id_dventa) {
        this.id_dventa = id_dventa;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getNombre(){
        return this.producto!=null ? this.producto.getNombre_producto():"---";

    }

}
