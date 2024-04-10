package com.example.RojasMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RojasMarketApplication {

	/*@Autowired
	private EmpleadoService empleadoService;*/
	public static void main(String[] args) {
		SpringApplication.run(RojasMarketApplication.class, args);
	}
/*
	@Override
	public void run(String... args) throws Exception {
		Empleado empleado = new Empleado();
		empleado.setFulldate_empleado("Cesar Andia Ascama");
		empleado.setDni("75743203");
		empleado.setContrasena_empleado("12345");
		empleado.setCorreo_empleado("zarek2807@gmail.com");
		empleado.setEdad_empleado(21);


		Rol rol  = new Rol();
				rol.setRolId(1);
				rol.setNombre("ADMIN");


				Set<EmpleadoRol> empleadoRoles = new HashSet<>();
				EmpleadoRol empleadoRol = new EmpleadoRol();
				empleadoRol.setRol(rol);
				empleadoRol.setEmpleado(empleado);
				empleadoRol.setEmpleado(empleado);
				empleadoRoles.add(empleadoRol);


				Empleado empleadoGuardado= empleadoService.guardarEmpleado(empleado, empleadoRoles);
		System.out.println(empleado.getFulldate_empleado());
	}*/
}
