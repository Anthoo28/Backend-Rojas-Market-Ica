package com.example.RojasMarket.service.Implement;

import com.example.RojasMarket.Model.ERole;
import com.example.RojasMarket.Repository.EmpleadoRepository;
import com.example.RojasMarket.Repository.RolRepository;
import com.example.RojasMarket.controller.Request.CreateEmpleadoDto;
import com.example.RojasMarket.entity.Empleado;
import com.example.RojasMarket.entity.Rol;
import com.example.RojasMarket.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado findById(Long id) {
        return empleadoRepository.findById(id).orElseThrow();
    }

    @Override
    public Empleado findByCorreo(String correo) {
        return empleadoRepository.findByCorreo(correo).orElse(null);
    }

    @Override
    public Empleado save(CreateEmpleadoDto empleadoDto) {

        Set<Rol> roles = empleadoDto.getRoles().stream()
                .map(role -> {
                    Optional<Rol> existingRole = rolRepository.findByName(ERole.valueOf(role));
                    return existingRole.orElseGet(() -> Rol.builder()
                            .name(ERole.valueOf(role))
                            .build());
                })
                .collect(Collectors.toSet());

        Empleado empleado = Empleado.builder()
                .correo(empleadoDto.getUsername())
                .contrasena(passwordEncoder.encode(empleadoDto.getPassword()))
                .roles(roles)
                .build();

        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado update(Long id, CreateEmpleadoDto empleadoDto) {

        Empleado user = empleadoRepository.findById(id).orElse(null);

        Set<Rol> roles = empleadoDto.getRoles().stream()
                .map(role -> Rol.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        assert user != null;
        user.setContrasena(passwordEncoder.encode(empleadoDto.getPassword()));
        user.setCorreo(empleadoDto.getEmail());
        user.setRoles(roles);

        return empleadoRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }
}
