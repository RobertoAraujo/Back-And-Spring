package io.github.robertoaraujo.dominio.repository.liquidacao;

import io.github.robertoaraujo.dominio.model.liquidacao.LiquidacaoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquidacaoReposytory extends PagingAndSortingRepository<LiquidacaoEntity, Long>, QueryByExampleExecutor<LiquidacaoEntity> {

}
