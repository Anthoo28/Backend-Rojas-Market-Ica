package com.example.RojasMarket.Repository;

import com.example.RojasMarket.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    @Query(value ="{call listaCliente()}", nativeQuery = true)
    List<Cliente> listaCliente();


    @Query(value = "{call IDCliente(:idIn)}", nativeQuery = true)
    Optional<Cliente> idCliente(@Param("idIn") int idIN);


    @Query(value = "{call saveCliente(:dni_clienteIn,:nombre_clienteIn,:correo_clienteIn,:telefono_clienteIn)}", nativeQuery = true)
    void saveCliente(
            @Param("dni_clienteIn") int dni_clienteIn,
            @Param("nombre_clienteIn") String nombre_clienteIn,
            @Param("correo_clienteIn") String correo_clienteIn,
            @Param("telefono_clienteIn") String telefono_clienteIn

    );

    @Modifying
    @Query(value = "{call EditCliente(:idIn, :dni_clienteIn, :nombre_clienteIn, :correo_clienteIn, :telefono_clienteIn)}", nativeQuery = true)
    void updateCliente(
            @Param("idIn") Long idIn,
            @Param("dni_clienteIn") int dni_clienteIn,
            @Param("nombre_clienteIn") String nombre_clienteIn,
            @Param("correo_clienteIn") String correo_clienteIn,
            @Param("telefono_clienteIn") String telefono_clienteIn
    );


    @Query(value = "{call DeleteCliente(:idIn)}", nativeQuery = true)
    void eliminarCliente(@Param("idIn") int idIN);
}
