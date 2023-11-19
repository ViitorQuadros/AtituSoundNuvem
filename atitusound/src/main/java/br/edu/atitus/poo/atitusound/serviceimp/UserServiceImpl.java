package br.edu.atitus.poo.atitusound.serviceimp;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.atitus.poo.atitusound.entities.UserEntity;
import br.edu.atitus.poo.atitusound.repositories.GenericRepository;
import br.edu.atitus.poo.atitusound.repositories.UserRepository;
import br.edu.atitus.poo.atitusound.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
		super();
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		
	}


	@Override
	public GenericRepository<UserEntity> getRepository() {
		return repository;
	}


	@Override
	public void validade(UserEntity entity) throws Exception {
		UserService.super.validade(entity);
		if(entity.getUsername() == null || entity.getUsername().isEmpty())
			throw new Exception("Campo Username Inválido");
		if(entity.getUuid() == null) {
			if(repository.existsByUsername(entity.getUsername()))
				throw new Exception ("Já existe um usuário com esse UserName");
		} else {
			if(repository.existsByNameAndUuidNot(entity.getUsername(), entity.getUuid()))
				throw new Exception ("Já existe um usuário com esse UserName");
		}
		
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado por esse username"));
		return user;
		
	}

	
	
	
}
