package io.github.robertoaraujo.service.usuario;

import io.github.robertoaraujo.dominio.model.user.UsuarioEntity;
import io.github.robertoaraujo.dominio.repository.usuario.UsuarioRepository;
import io.github.robertoaraujo.rest.dto.UsuarioDto;
import io.github.robertoaraujo.service.exeptions.BadRequestException;
import io.github.robertoaraujo.service.exeptions.OjetoNotFoundExeption;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;

    private UsuarioRepository repository;


    public UsuarioEntity salvar(UsuarioDto dto) {
        return repository.save(dto.toEntity());
    }

    public Page<UsuarioEntity> listar(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public UsuarioEntity buscar(Long id) {
        Optional<UsuarioEntity> byId = repository.findById(id);

        if (byId.isEmpty()) {
            throw new BadRequestException("ID inválido");
        }

        return byId.orElseThrow(() -> new OjetoNotFoundExeption("Usuário não encontrado"));
    }

    public UsuarioEntity atualizar(Long id, UsuarioDto dto) {

        if (dto.toEntity().getNome().isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        } else if (dto.toEntity().getEmail().isBlank()) {
            throw new IllegalArgumentException("O email não pode ser vazio");
        } else if (!dto.toEntity().getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("O email não é um tipo válido");
        } else if (!dto.toEntity().getEmail().contains("@")) {
            throw new IllegalArgumentException("O email não é um tipo válido");
        } else {
            if (dto.toEntity().getSenha().isBlank()) {
                throw new IllegalArgumentException("A senha não pode ser vazia");
            } else if (dto.toEntity().getSenha().length() < 8) {
                throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres");
            }
        }

        UsuarioEntity usuario = buscar(id);
        usuario.setNome(dto.toEntity().getNome());
        usuario.setEmail(dto.toEntity().getEmail());
        usuario.setSenha(dto.toEntity().getSenha());
        UsuarioEntity ususarioUp = repository.save(usuario);

        return ususarioUp;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
