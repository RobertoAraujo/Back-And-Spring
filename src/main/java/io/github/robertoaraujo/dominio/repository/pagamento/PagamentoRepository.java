package io.github.robertoaraujo.dominio.repository.pagamento;

import io.github.robertoaraujo.dominio.model.pagamento.PagamentoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends PagingAndSortingRepository<PagamentoEntity, Long>, QueryByExampleExecutor<PagamentoEntity> {
}
