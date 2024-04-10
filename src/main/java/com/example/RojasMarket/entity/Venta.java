package com.example.RojasMarket.entity;

import com.example.RojasMarket.DTO.ReporteVentaDTO;
import com.example.RojasMarket.Model.IVentaDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Venta {
    @Id
    @Column(name = "id_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    private String estado;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado empleado;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venta")
    private List<DetalleVenta> items;

    private Double igv;
    private Double total;

    public Venta() {
        items = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.fechaVenta = new Date();
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getItems() {
        return items;
    }

    public void setItems(List<DetalleVenta> items) {
        this.items = items;
        calcularTotalEIGV();
    }

    public String getIgv() {
        return formatDecimal(igv);
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public String getTotal() {
        return formatDecimal(total);
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void calcularTotalEIGV() {
        double total = 0.0;
        for (DetalleVenta detalle : items) {
            double subtotal = detalle.getCantidad() * detalle.getProducto().getPrecio_salida_producto();
            total += subtotal;

            if ("aprobado".equals(estado)) {
                int cantidadActual = detalle.getProducto().getCantidad_producto();
                detalle.getProducto().setCantidad_producto(cantidadActual - detalle.getCantidad());
            }
        }

        double igv = total * 0.18;

        this.total = Math.round((total + igv) * 100.0) / 100.0;
        this.igv = Math.round(igv * 100.0) / 100.0;
    }

    private String formatDecimal(Double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(value);
    }

    public List<ReporteVentaDTO> reportPdf(){

        List<ReporteVentaDTO> reporteVentaDTOList= new ArrayList<>();
        this.items.forEach(i->{
            ReporteVentaDTO report= new ReporteVentaDTO();
            report.setNombre(i.getProducto().getNombre_producto());
            report.setCantidad(i.getCantidad());
            report.setPrecio((double) i.getProducto().getPrecio_salida_producto());
            report.setSubTotal((double)((float)i.getCantidad()*i.getProducto().getPrecio_salida_producto()));
            reporteVentaDTOList.add(report);
        });
        return reporteVentaDTOList;
    }










}
