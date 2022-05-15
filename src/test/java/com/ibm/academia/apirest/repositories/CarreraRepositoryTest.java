package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDumny;
import com.ibm.academia.apirest.entities.Carrera;

@DataJpaTest
public class CarreraRepositoryTest 
{
	@Autowired
	private CarreraRepository carreraRepository;
	
	void setup()
	{
		
	}
	
	@Test
	@DisplayName("Test: Busca carrera por nombre")
	void findCarrerasByNombreContains()
	{
		//Given
		carreraRepository.save(DatosDumny.carrera01());
		carreraRepository.save(DatosDumny.carrera02());
		carreraRepository.save(DatosDumny.carrera03());
		
		//When
		Iterable<Carrera> expected = carreraRepository.findCarrerasByNombreContains("Sistemas");
		
		//Then
		assertThat(((List<Carrera>)expected).size() == 2).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar carrera por nombre No case sensitive")
	void findCarrerasByNombreContainsIgnoreCase()
	{
		//Given
		carreraRepository.save(DatosDumny.carrera01());
		carreraRepository.save(DatosDumny.carrera02());
		carreraRepository.save(DatosDumny.carrera03());
				
		//When
		List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByNombreContainsIgnoreCase("sistemas");
				
		//Then
		assertThat(expected.size() == 2).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar carreras mayores a 14 años")
	void findCarrerasByCantidadAniosAfter()
	{
		//Given
		carreraRepository.save(DatosDumny.carrera01());
		carreraRepository.save(DatosDumny.carrera02());
		carreraRepository.save(DatosDumny.carrera03());
						
		//When
		List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByCantidadAniosAfter(4);
						
		//Then
		assertThat(expected.size() == 2).isTrue();
	}
}
