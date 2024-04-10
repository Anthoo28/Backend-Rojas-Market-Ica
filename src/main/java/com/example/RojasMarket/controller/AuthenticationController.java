package com.example.RojasMarket.controller;

import com.example.RojasMarket.Repository.EmpleadoRepository;
import com.example.RojasMarket.Security.jwt.JwtUtils;
import com.example.RojasMarket.entity.Empleado;
import com.example.RojasMarket.entity.JwtRequest;
import com.example.RojasMarket.entity.JwtResponse;
import com.example.RojasMarket.service.Implement.EmpleadoDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmpleadoDetailsServiceImpl empleadoDetailsService;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private JwtUtils jwtUtils;



    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (DisabledException e) {
            throw new Exception("Usuario deshabilitado", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales inválidas", e);
        }

        UserDetails userDetails = empleadoDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtils.generateAccessToken(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @GetMapping("/actual-usuario")
    public Empleado obtenerUsuarioActual(Authentication authentication) {
        String correo = authentication.getName();
        Optional<Empleado> empleado = empleadoRepository.findByCorreo(correo);
        return empleado.orElse(null);
    }



}
