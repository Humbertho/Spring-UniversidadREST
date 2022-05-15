package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/alumno")
public class AlumnoController 
{
	@Autowired
	@Qualifier("alumnoDAOimpl")
	private PersonaDAO alumnoDao;
	
	@Autowired
	private CarreraDAO carreraDao;
	
	/**
	 * Endpint para crear un objeto Persona de tipo alumno
	 * @param alumno Objeto alumno con la informacion a crear 
	 * @return Retorna un objeto Persona de tipo alumno con codigo HttStatus 201
	 * @author Humberto 12-05-2022
	 */	
	@PostMapping
	public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno)
	{
		Persona alumnoGuardado = alumnoDao.guardar(alumno);
		return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para listar los alumnos 
	 * @NotFoundException En caso de que falle actualizando el objeto de alumno
	 * @return Retorna una lista de tipo alumno con la información actualizada
	 * @author Humberto 12-05-2022
	 */
	@GetMapping("/alumnos/lista")
	public ResponseEntity<?> obtenerTodos()
	{
		List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
		
		if(alumnos.isEmpty())
			throw new NotFoundException("No existe alumnos");
		
		return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
	}
	
	/**
	* Obtener alumnos por Id
	 * @param aulaId Parametro para buscar la aula
	 * @NotFoundException En caso de que falle actualizando el objeto de alumno
	 * @return Retorna una lista de tipo alumno con la información actualizada
	 * @author Humberto
	 */
	@GetMapping("/alumnoId/{alumnoId}")
	public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Integer alumnoId)
	{
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);		
		
		if(oAlumno.isPresent())
			throw new NotFoundException(String.format("Alumno con id %d no existe", alumnoId));
		
		return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
	}
	
	/**
	 * Actualizar un alumno
	 * @param alumnoId Parametro para buscar la alumno
	 * @param alumno   objeto (json con la informacion a modificar)
	 * @NotFoundException En caso de que falle actualizando el objeto de alumno
	 * @return Retorna un objeto de tipo alumno con la información actualizada
	 * @author Humberto
	 */
	@PutMapping("/upd/alumnoId/{alumnoId}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer alumnoId, @RequestBody Persona alumno)
	{
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);		
		
		if(oAlumno.isPresent())
			throw new NotFoundException(String.format("Alumno con id %d no existe", alumnoId));
		
		Persona alumnoActualizado = ((AlumnoDAO)alumnoDao).actualizar(oAlumno.get(), alumno);
		return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
	}
	
	/**
	 * eliminar un alumno
	 * @param alumnoId Parametro para eliminar la aula
	 * @NotFoundException En caso de que falle eliminacion el objeto de alumno
	 * @return Retorna un mensaje con la confirmación de la eliminación
	 * @author Humberto
	 */
	@DeleteMapping("/alumnoId/{alumnoId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Integer alumnoId)
	{
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);		
		
		if(oAlumno.isPresent())
			throw new NotFoundException(String.format("Alumno con id %d no existe", alumnoId));
		
		alumnoDao.eliminarPorId(oAlumno.get().getId());
		
		return new ResponseEntity<String>("Alumno ID: " + alumnoId + " se elimino satisfactoriamente", HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Asignar una carrera a un alumno
	 * @param alumnoId Parametro para buscar el alumno
	 * @param carreraId   Parametro para buscar la carrera
	 * @NotFoundException En caso de que falle actualizando el objeto de carrera o alumno
	 * @return Retorna un objeto de tipo alumno con la información actualizada
	 * @author Humberto
	 */
	@PutMapping("/alumnoId/{alumnoId}/carrera/{carreraId}")
	public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer carreraId, @PathVariable Integer alumnoId)
	{
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);		
		
		if(oAlumno.isPresent())
			throw new NotFoundException(String.format("Alumno con id %d no existe", alumnoId));
		
		Optional<Carrera> oCarrera =  carreraDao.buscarPorId(carreraId);
		
		if(!oCarrera.isPresent())
		{
			throw new BadRequestException(String.format("La carrera con Id: %d no existe", carreraId));
		}
		
		Persona alumno = ((AlumnoDAO)alumnoDao).asociarCarreraAlumno(oAlumno.get(), oCarrera.get());
		
		return new ResponseEntity<Persona>(alumno, HttpStatus.OK);
	}
}
