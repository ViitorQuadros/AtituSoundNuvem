package br.edu.atitus.poo.atitusound.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import br.edu.atitus.poo.atitusound.entities.GenericEntity;
import br.edu.atitus.poo.atitusound.repositories.GenericRepository;

public interface GenericService<TEntidade extends GenericEntity> {
	
	
	GenericRepository<TEntidade> getRepository();
	
	default void validade (TEntidade entity) throws Exception{
		if (entity.getName() == null || entity.getName().isEmpty())
			throw new Exception("Campo nome Inválido");
		if (entity.getUuid() == null) {
			if(getRepository().existsByName(entity.getName()))
				throw new Exception("Não existe registro com esse nome!!");
		} else {
			if (!getRepository().existsById(entity.getUuid()))
				throw new Exception("Não existe registro com esse UUID");
			if (getRepository().existsByNameAndUuidNot(entity.getName(), entity.getUuid()))
				throw new Exception("Já existe registro com esse nome!");
		}
		
		
	}
	
	
	default TEntidade save(TEntidade entity) throws Exception {
		validade(entity);
		getRepository().save(entity);
		return entity;
	}

	
	default List<TEntidade> findAll() throws Exception {
	
		return getRepository().findAll();
	}

	
	
	default void validadeFindByName(String name, Pageable pageable) throws Exception{
		
	}
	
	default Page<List<TEntidade>> findByNameContainingIgnoreCase(String name, Pageable pageable) throws Exception {
		validadeFindByName(name, pageable);
		return getRepository().findByNameContainingIgnoreCase(name, pageable);
	}

	
	default void validadeFindById(UUID uuid) throws Exception{
		
	}
	
	
	default Optional<TEntidade> findById(UUID uuid) throws Exception {
		validadeFindById(uuid);
		return getRepository().findById(uuid);
	}

	
	default void validateDeleteById(UUID uuid) throws Exception{
		if(!getRepository().existsById(uuid))
			throw new Exception("Não existe registro com esse UUID");
	}
	
	
	
	default void deleteById(UUID uuid) throws Exception{
		getRepository().deleteById(uuid);
		
	}
	
}
