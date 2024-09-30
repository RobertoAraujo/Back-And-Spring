package io.github.robertoaraujo.dominio.model.user;

import io.github.robertoaraujo.dominio.model.empenho.EmpenhoEntity;
import io.github.robertoaraujo.dominio.model.liquidacao.LiquidacaoEntity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@ApiModel(value = "Usuario", description = "Essa entidade não será exibida no Swagger")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "token")
    private String token;

    @OneToMany(fetch = FetchType.EAGER)
    private List<EmpenhoEntity> empenho;

    public UsuarioEntity(Long id, String nome, String email, String senha, String token, List<EmpenhoEntity> empenho) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.token = token;
        this.empenho = empenho;
    }

}
