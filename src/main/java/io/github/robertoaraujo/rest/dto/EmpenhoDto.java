package io.github.robertoaraujo.rest.dto;

import io.github.robertoaraujo.dominio.model.empenho.EmpenhoEntity;
import io.github.robertoaraujo.dominio.model.user.UsuarioEntity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ApiModel(value = "Empenho-DTO", description = "Objeto de transferência de dados para Empenho")
public record EmpenhoDto(
        @NotBlank(message = "Número do empenho é obrigatório")
        String numeroEmpenho,

        @NotNull(message = "Data do empenho é obrigatória")
        Date dataEmpenho,

        @NotNull(message = "Data de vencimento é obrigatória")
        Date dataVencimento,

        @NotNull(message = "Valor do empenho é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal valorEmpenho,

        @NotNull(message = "Usuário é obrigatório")
        Long usuarioId) {

    public EmpenhoEntity toEntity() {
        String numeroEmpenhoGerado = gerarNumeroEmpenho();
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(usuarioId);
        return new EmpenhoEntity(numeroEmpenhoGerado, dataEmpenho, dataVencimento, valorEmpenho, usuario);
    }

    public String gerarNumeroEmpenho() {
        long numeroEmpenho = (long) (Math.random() * 9000000000L) + 1000000000L;
        return Long.toString(numeroEmpenho);
    }
}