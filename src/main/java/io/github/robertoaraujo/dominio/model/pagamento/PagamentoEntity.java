package io.github.robertoaraujo.dominio.model.pagamento;

import io.github.robertoaraujo.dominio.model.liquidacao.LiquidacaoEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tb_pagamento")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_pagamento")
    private String numeroPagamento;

    @Column(name = "data_pagamento")
    private String dataPagamento;

    @Column(name = "valor_pagamento")
    private String valorPagamento;

    @Column(name = "tipo_pagamento")
    private String tipoPagamento;

    @Column(name = "status_pagamento")
    private String statusPagamento;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "data_vencimento")
    private String dataVencimento;

    @ManyToOne
    @JoinColumn(name = "liquidacao_id")
    private LiquidacaoEntity liquidacao;
}