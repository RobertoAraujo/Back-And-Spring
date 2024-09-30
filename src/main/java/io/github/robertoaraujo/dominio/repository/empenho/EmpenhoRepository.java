package io.github.robertoaraujo.dominio.repository.empenho;

import io.github.robertoaraujo.dominio.model.empenho.EmpenhoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpenhoRepository extends PagingAndSortingRepository<EmpenhoEntity, Long>, QueryByExampleExecutor<EmpenhoEntity> {



}
