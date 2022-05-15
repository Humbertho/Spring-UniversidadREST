package com.ibm.academia.apirest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Direccion;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;

@SpringBootApplication
public class UniversidadRestApplication 
{
	
	public static void main(String[] args) 
	{
		SpringApplication.run(UniversidadRestApplication.class, args);
	}
	
	/*@Bean
	public CommandLineRunner runner()
	{
		return args -> {
			Direccion direccion = new Direccion("calle 25", "36", "126", null, null, "campin");
			Persona alumno = new Alumno(null, "Mario", "Moreno", "987654321", direccion);
			
			Persona personaGuardada = alumnoDao.gusrdar(alumno);
			System.out.println(personaGuardada.toString());
			
			List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
			alumnos.forEach(System.out::println);
						
		};
	}*/

}
