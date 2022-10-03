package com.icaetano.beerprint.security.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.icaetano.beerprint.security.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioViewForm {
    
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String username;
	
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String email;
	
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String password;

    public Usuario convert() {
		return new Usuario(username, email, new BCryptPasswordEncoder().encode(password));
	}

}