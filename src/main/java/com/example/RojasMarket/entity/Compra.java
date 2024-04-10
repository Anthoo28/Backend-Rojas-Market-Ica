package com.example.RojasMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Compras")
@AllArgsConstructor
@Builder
@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompra")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaCompra;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "compra_id")
    private List<DetalleCompra> items1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;


    private Double igv;
    private Double total;

    public Compra() {
        items1 = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.fechaCompra = new Date();
    }

    @Transactional
    public void calcularTotalEIGV() {
        double total = 0.0;
        for (DetalleCompra detalle : items1) {
            double subtotal = detalle.getCantidad() * detalle.getPrecio_unitario();
            total += subtotal;

            int cantidadActual = detalle.getProducto().getCantidad_producto();
            detalle.getProducto().setCantidad_producto(cantidadActual + detalle.getCantidad());

            float precioUnitario = detalle.getPrecio_unitario();
            detalle.getProducto().setPrecio_ingreso_producto(precioUnitario);

            // Guardar o actualizar el producto en la base de datos si es necesario
            // productoRepository.save(detalle.getProducto());
        }

        double igv = total * 0.18;

        this.total = Math.round((total + igv) * 100.0) / 100.0;
        this.igv = Math.round(igv * 100.0) / 100.0;
    }


    private String formatDecimal(Double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(value);
    }


}
