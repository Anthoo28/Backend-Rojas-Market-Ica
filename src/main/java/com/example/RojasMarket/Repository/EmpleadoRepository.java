package com.example.RojasMarket.Repository;

import com.example.RojasMarket.entity.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByCorreo(String correo);




}
