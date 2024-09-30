package io.github.robertoaraujo.rest.dto;

import io.github.robertoaraujo.dominio.model.liquidacao.LiquidacaoEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public record LiquidacaoDto(
        @NotNull
        @NotBlank(message = "Data da liquidação é obrigatória")
        Date dataLiquidacao,
        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal valorLiquidacao) {

    public LiquidacaoEntity toEntity() {
//
        return null;
    }

    public String gerarNumeroLiquidacao() {
        long numeroLiquidacao = (long) (Math.random() * 9000000000L) + 1000000000L;
        return Long.toString(numeroLiquidacao);
    }
}