package io.github.robertoaraujo.rest.dto;

import io.github.robertoaraujo.dominio.model.user.UsuarioEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@ApiModel(value = "Usuario-DTO", description = "Objeto de transferência de dados para Usuário")
public record UsuarioDto(

        @NotBlank(message = "O nome não pode ser vazio")
        @Column(name = "nome")
        @ApiModelProperty(value = "Nome completo do usuário", required = true, example = "Roberto A Moraes")
        String nome,

        @NotBlank(message = "O email não pode ser vazio")
        @Size(min = 1, max = 255)
        @Column(name = "email")
        @Email(message = "Email inválido")
        @ApiModelProperty(value = "Email do usuário", required = true, example = "endriosrobert@gmail.com")
        String email,

        @NotBlank(message = "A senha não pode ser vazia")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        @Column(name = "senha")
        @ApiModelProperty(value = "Senha do usuário", required = true, example = "senha123")
        String senha) {

    public UsuarioEntity toEntity() {
        String senhaHash = hashMD5(senha);
        return new UsuarioEntity(null, nome, email, senhaHash, null, null);
    }

    private String hashMD5(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash MD5", e);
        }
    }

}
