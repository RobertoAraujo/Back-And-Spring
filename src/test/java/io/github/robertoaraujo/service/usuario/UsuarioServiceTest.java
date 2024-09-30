package io.github.robertoaraujo.service.usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.github.robertoaraujo.dominio.model.user.UsuarioEntity;
import io.github.robertoaraujo.dominio.repository.usuario.UsuarioRepository;
import io.github.robertoaraujo.rest.dto.UsuarioDto;
import io.github.robertoaraujo.service.exeptions.OjetoNotFoundExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvar() {
        UsuarioDto dto = new UsuarioDto("Nome Teste", "email@teste.com", "senha123");
        UsuarioEntity entity = dto.toEntity();

        when(repository.save(any(UsuarioEntity.class))).thenReturn(entity);

        UsuarioEntity savedEntity = usuarioService.salvar(dto);

        assertNotNull(savedEntity);
        assertEquals("Nome Teste", savedEntity.getNome());
        verify(repository, times(1)).save(any(UsuarioEntity.class));
    }

    @Test
    void listar() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<UsuarioEntity> page = new PageImpl<>(Arrays.asList(new UsuarioEntity(), new UsuarioEntity()));

        when(repository.findAll(pageRequest)).thenReturn(page);

        Page<UsuarioEntity> result = usuarioService.listar(pageRequest);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(repository, times(1)).findAll(pageRequest);
    }

    @Test
    void buscarUsuarioExistente() {
        Long id = 1L;
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(usuario));

        UsuarioEntity foundUsuario = usuarioService.buscar(id);

        assertNotNull(foundUsuario);
        assertEquals(id, foundUsuario.getId());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void buscarUsuarioNaoExistente() {
        Long id = 1L;

        when(repository.findById(id)).thenThrow(new OjetoNotFoundExeption("Usuário não encontrado"));

        assertThrows(OjetoNotFoundExeption.class, () -> usuarioService.buscar(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void atualizarUsuarioValido() {
        Long id = 1L;
        UsuarioEntity usuarioExistente = new UsuarioEntity();
        usuarioExistente.setId(id);
        usuarioExistente.setNome("Nome Atual");

        UsuarioDto dto = new UsuarioDto("Novo Nome", "novoemail@teste.com", "novaSenha123");

        when(repository.findById(id)).thenReturn(Optional.of(usuarioExistente));
        when(repository.save(any(UsuarioEntity.class))).thenReturn(usuarioExistente);

        UsuarioEntity updatedUsuario = usuarioService.atualizar(id, dto);

        assertNotNull(updatedUsuario);
        assertEquals("Novo Nome", updatedUsuario.getNome());
        verify(repository, times(1)).save(any(UsuarioEntity.class));
    }

    @Test
    void deletarUsuarioExistente() {
        Long id = 1L;

        doNothing().when(repository).deleteById(id);

        usuarioService.deletar(id);

        verify(repository, times(1)).deleteById(id);
    }
}