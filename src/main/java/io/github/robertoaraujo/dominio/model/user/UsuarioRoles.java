package io.github.robertoaraujo.dominio.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsuarioRoles {
    ADMIN("ADMIN"), USER("user");

    private String role;

}
