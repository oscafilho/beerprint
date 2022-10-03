package com.icaetano.beerprint.security.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.icaetano.beerprint.security.model.Usuario;
import com.icaetano.beerprint.security.repository.UsuarioRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String password;

	public Usuario atualiza(Long id, UsuarioRepository usuarioRepository) {

		Usuario usuario = usuarioRepository.findById(id).get();
		usuario.setPassword(new BCryptPasswordEncoder().encode(this.password));

		return usuario;

	}

}