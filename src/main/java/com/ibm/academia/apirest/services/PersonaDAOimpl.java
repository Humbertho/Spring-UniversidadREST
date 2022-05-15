package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;

public class PersonaDAOimpl extends GenericoDAOimpl<Persona, PersonaRepository> implements PersonaDAO
{

	public PersonaDAOimpl(PersonaRepository repository) 
	{
		super(repository);
	}

	@Override
	public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido) 
	{
		return repository.buscarPorNombreYApellido(nombre, apellido);
	}

	@Override
	public Optional<Persona> buscarPorDni(String dni) 
	{
		return repository.buscarPorDni(dni);
	}

	@Override
	public Iterable<Persona> buscarPersonaPorApellido(String apellido) 
	{
		return repository.buscarPersonaPorApellido(apellido);
	}

}
