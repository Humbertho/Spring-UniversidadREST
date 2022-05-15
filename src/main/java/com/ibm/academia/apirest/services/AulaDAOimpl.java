package com.ibm.academia.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.AulaRepository;

@Service
public class AulaDAOimpl extends GenericoDAOimpl<Aula, AulaRepository> implements AulaDAO 
{

	public AulaDAOimpl(AulaRepository repository) 
	{
		super(repository);
	}

	@Override
	public List<Aula> buscarPorTipoPizarron(String pizarron) 
	{
		return repository.buscarPorPizarron(pizarron);
	}

	@Override
	public List<Aula> buscarPorNombrePabellon(String pabellon) 
	{
		return repository.buscarPorNombrePabellon(pabellon);
	}

	@Override
	@Transactional
	public Aula actualizar(Aula aulaEncontrado, Aula aula) 
	{
		Aula aulaActualizado = null;
		aulaEncontrado.setCantidadPupitres(aula.getCantidadPupitres());
		aulaEncontrado.setMedidas(aula.getMedidas());
		aulaEncontrado.setNumeroAula(aula.getNumeroAula());
		aulaEncontrado.setPizarron(aula.getPizarron());
		aulaActualizado = repository.save(aulaEncontrado);
		
		return aulaActualizado;
	}
}
