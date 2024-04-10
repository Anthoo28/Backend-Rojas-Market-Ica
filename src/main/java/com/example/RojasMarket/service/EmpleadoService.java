package com.example.RojasMarket.service;

import com.example.RojasMarket.controller.Request.CreateEmpleadoDto;
import com.example.RojasMarket.entity.Empleado;

import java.util.List;

public interface EmpleadoService {
    List<Empleado> findAll();
    Empleado findById(Long id);
    Empleado findByCorreo(String correo);
    Empleado save(CreateEmpleadoDto empleadoDto);
    Empleado update(Long id, CreateEmpleadoDto empleadoDto);
    void deleteById(Long id);
}
