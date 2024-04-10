    package com.example.RojasMarket.entity;


    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "clientes")
    public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente ;
    private  int dni_cliente;
    private String nombre_cliente;
    private String correo_cliente;

    @Column(length = 9)
    private String telefono_cliente;



    }
