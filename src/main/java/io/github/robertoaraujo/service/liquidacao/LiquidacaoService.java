package io.github.robertoaraujo.service.liquidacao;

import io.github.robertoaraujo.dominio.model.liquidacao.LiquidacaoEntity;
import io.github.robertoaraujo.dominio.repository.empenho.EmpenhoRepository;
import io.github.robertoaraujo.dominio.repository.liquidacao.LiquidacaoReposytory;
import io.github.robertoaraujo.rest.dto.LiquidacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class LiquidacaoService {

    @Autowired
    private LiquidacaoReposytory reposytory;


    public LiquidacaoEntity salvar(LiquidacaoDto dto) {
        return  reposytory.save(dto.toEntity());
    }

    public Page<LiquidacaoEntity> listar(PageRequest pageRequest) {
        Page<LiquidacaoEntity> all = reposytory.findAll(pageRequest);
        if (all.isEmpty()) {
            throw new RuntimeException("Nenhuma liquidação encontrada");
        }
        return reposytory.findAll(pageRequest);
    }

    public LiquidacaoEntity buscarPorId(Long id) {
        return reposytory.findById(id).orElseThrow();
    }
}
