package io.github.robertoaraujo.dominio.model.empenho;

import io.github.robertoaraujo.dominio.model.liquidacao.LiquidacaoEntity;
import io.github.robertoaraujo.dominio.model.user.UsuarioEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_empenho")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmpenhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_empenho", nullable = false)
    private String numeroEmpenho;

    @Column(name = "data_empenho", nullable = false)
    private Date dataEmpenho;

    @Column(name = "data_vencimento", nullable = false)
    private Date dataVencimento;

    @Column(name = "valor_empenho", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valorEmpenho;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "empenho", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LiquidacaoEntity> liquidacoes;

    // Construtor adicional sem o campo 'id' para facilitar a criação de novos objetos
    public EmpenhoEntity(String numeroEmpenho, Date dataEmpenho, Date dataVencimento, BigDecimal valorEmpenho, UsuarioEntity usuario) {
        this.numeroEmpenho = numeroEmpenho;
        this.dataEmpenho = dataEmpenho;
        this.dataVencimento = dataVencimento;
        this.valorEmpenho = valorEmpenho;
        this.usuario = usuario;
    }
}