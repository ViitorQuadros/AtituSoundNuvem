package br.edu.atitus.poo.atitusound.serviceimp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.atitus.poo.atitusound.entities.ArtistEntity;
import br.edu.atitus.poo.atitusound.repositories.ArtistRepository;
import br.edu.atitus.poo.atitusound.repositories.GenericRepository;
import br.edu.atitus.poo.atitusound.service.ArtistService;

@Service
public abstract class ArtistServiceImpl implements ArtistService{

	private final ArtistRepository repository;
	
	public ArtistServiceImpl(ArtistRepository repository) {
		super();
		this.repository = repository;
	}
	
	

	

}
