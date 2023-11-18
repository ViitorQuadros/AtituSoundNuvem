package br.edu.atitus.poo.atitusound.serviceimp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.atitus.poo.atitusound.entities.ArtistEntity;
import br.edu.atitus.poo.atitusound.repositories.ArtistRepository;
import br.edu.atitus.poo.atitusound.service.ArtistService;

@Service
public class ArtistServiceImpl implements ArtistService{

	private final ArtistRepository repository;
	
	public ArtistServiceImpl(ArtistRepository repository) {
		super();
		this.repository = repository;
	}

	protected void validade (ArtistEntity entity) throws Exception{
		if (entity.getName() == null || entity.getName().isEmpty())
			throw new Exception("Campo nome Inválido");
		if (entity.getUuid() == null) {
			if(repository.existsByName(entity.getName()))
				throw new Exception("Não existe registro com esse nome!!");
		} else {
			if (!repository.existsById(entity.getUuid()))
				throw new Exception("Não existe registro com esse UUID");
			if (repository.existsByNameAndUuidNot(entity.getName(), entity.getUuid()))
				throw new Exception("Já existe registro com esse nome!");
		}
		
		
	}
	
	@Override
	public ArtistEntity save(ArtistEntity entity) throws Exception {
		validade(entity);
		repository.save(entity);
		return entity;
	}

	@Override
	public List<ArtistEntity> findAll() throws Exception {
	
		return repository.findAll();
	}

	@Override
	public Page<List<ArtistEntity>> findByNameContainingIgnoreCase(String name, Pageable pageable) throws Exception {
		
		return repository.findByNameContainingIgnoreCase(name, pageable);
	}

	@Override
	public Optional<ArtistEntity> findById(UUID uuid) throws Exception {
		
		return repository.findById(uuid);
	}

}
