package com.icaetano.beerprint.security.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.icaetano.beerprint.security.model.Usuario;

import lombok.Getter;

@Getter
public class UsuarioDTO {
    private Long id;
    private String username;
    private String email;

    public UsuarioDTO(Usuario usu) {
        this.id = usu.getId();
        this.username = usu.getUsername();
        this.email = usu.getEmail();
    }


    public static List<UsuarioDTO> converter(List<Usuario> usuarios) {

        return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }
}