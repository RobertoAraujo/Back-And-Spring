package io.github.robertoaraujo.dominio.model.liquidacao;

import io.github.robertoaraujo.dominio.model.empenho.EmpenhoEntity;
import io.github.robertoaraujo.dominio.model.pagamento.PagamentoEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_liquidacao")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class LiquidacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_liquidacao")
    private String numeroLiquidacao;

    @Column(name = "data_liquidacao")
    private Date dataLiquidacao;

    @Column(name = "valor_liquidacao")
    private BigDecimal valorLiquidacao;

    @ManyToOne
    @JoinColumn(name = "empenho_id")
    private EmpenhoEntity empenho;


    public LiquidacaoEntity(Long id, String numeroLiquidacao, Date dataLiquidacao, BigDecimal valorLiquidacao) {
        this.id = id;
        this.numeroLiquidacao = numeroLiquidacao;
        this.dataLiquidacao = dataLiquidacao;
        this.valorLiquidacao = valorLiquidacao;
    }

}