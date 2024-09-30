package io.github.robertoaraujo.service.empenho;

import io.github.robertoaraujo.dominio.model.empenho.EmpenhoEntity;
import io.github.robertoaraujo.dominio.repository.empenho.EmpenhoRepository;
import io.github.robertoaraujo.rest.dto.EmpenhoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EmpenhoService {

    @Autowired
    private EmpenhoRepository empenhoRepository;

    public EmpenhoEntity salvar(EmpenhoDto dto) {
        return empenhoRepository.save(dto.toEntity());
    }

    public EmpenhoEntity findById(Long id) {
        return empenhoRepository.findById(id).orElse(null);
    }

    public Page<EmpenhoEntity> listar(PageRequest pageRequest) {
        return empenhoRepository.findAll(pageRequest);
    }
}
