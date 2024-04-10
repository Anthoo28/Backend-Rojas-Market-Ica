package com.example.RojasMarket.controller;

import com.example.RojasMarket.controller.Request.CreateEmpleadoDto;
import com.example.RojasMarket.entity.Empleado;
import com.example.RojasMarket.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
@CrossOrigin("*")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Empleado>> findAll() {
        return ResponseEntity.ok(empleadoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Empleado> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(empleadoService.findById(id));
    }

    @PostMapping("/create")
  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Empleado> createUser(@Valid @RequestBody CreateEmpleadoDto createEmpleadoDto) {
        return ResponseEntity.ok(empleadoService.save(createEmpleadoDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Empleado> updateUser(@PathVariable("id") Long id, @RequestBody CreateEmpleadoDto createEmpleadoDto) {
        return ResponseEntity.ok(empleadoService.save(createEmpleadoDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole( 'ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        empleadoService.deleteById(id);
    }




}
