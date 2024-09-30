package io.github.robertoaraujo.dominio.repository.usuario;

import io.github.robertoaraujo.dominio.model.user.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<UsuarioEntity, Long>, QueryByExampleExecutor<UsuarioEntity> {

}