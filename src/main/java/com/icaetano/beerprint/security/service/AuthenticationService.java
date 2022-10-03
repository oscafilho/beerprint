package com.icaetano.beerprint.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.icaetano.beerprint.security.model.Usuario;
import com.icaetano.beerprint.security.repository.UsuarioRepository;
import com.icaetano.beerprint.security.utils.Constant;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = repository.findByUsername(username);
		if(usuario.isPresent()) {
			return usuario.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidos");
	}

    public UserDetails loadUserByUsernameManual(String username) {

        if (!username.equals("osvaldo")) {
            throw new UsernameNotFoundException("Username not found!");
        }

        final Usuario user = new Usuario();
        // user.setId(new Long(1));
        user.setUsername("osvaldo");
        user.setUserRole(Constant.UserRole.ROLE_USER);
        user.setPassword("$2a$12$WGGjDBSXAH8xulWM.HjfE.erMRjp6EJiiAGR203i6AWSnPuJKfLO."); // welCome1/

        return user;
    }

}