package io.github.robertoaraujo.rest.controller;

import io.github.robertoaraujo.dominio.model.user.UsuarioEntity;
import io.github.robertoaraujo.rest.dto.UsuarioDto;
import io.github.robertoaraujo.service.usuario.UsuarioService;
import io.github.robertoaraujo.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController controller;

    @Mock
    private UsuarioService service;

    @Mock
    private JwtUtil jwtUtil;

    private UsuarioEntity usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new UsuarioEntity(1L, "João", "joao@example.com", "senha123", null, null);
    }

    @Test
    void salvar() {
        UsuarioDto dto = new UsuarioDto("João", "joao@example.com", "senha123");
        when(service.salvar(any(UsuarioDto.class))).thenReturn(usuario);

        ResponseEntity<UsuarioDto> response = controller.salvar(dto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto.nome(), response.getBody().nome());
        assertEquals(dto.email(), response.getBody().email());
    }

    @Test
    void listar() {
        PageRequest pageRequest = PageRequest.of(0, 500);
        Page<UsuarioEntity> page = new PageImpl<>(Collections.singletonList(usuario));
        when(service.listar(any(PageRequest.class))).thenReturn(page);

        ResponseEntity<?> response = controller.listar(0, 500);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, ((Page<?>) response.getBody()).getTotalElements());
    }

    @Test
    void buscar() {
        when(service.buscar(1L)).thenReturn(usuario);
        // testando a geração de token
        when(jwtUtil.generateToken(anyString())).thenReturn("fake-jwt-token");

        ResponseEntity<?> response = controller.buscar(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UsuarioEntity result = (UsuarioEntity) response.getBody();
        assertEquals(usuario.getNome(), result.getNome());
        // testando a geração de token
        assertEquals("fake-jwt-token", result.getToken());
    }

    @Test
    void atualizar() {
        UsuarioDto dto = new UsuarioDto("João Atualizado", "joaoatualizado@example.com", "novaSenha123");
        UsuarioEntity usuarioAtualizado = new UsuarioEntity(1L, dto.nome(), dto.email(), dto.senha(), null, null);

        when(service.atualizar(eq(1L), any(UsuarioDto.class))).thenReturn(usuarioAtualizado);
        when(jwtUtil.generateToken(anyString())).thenReturn("fake-jwt-token");

        ResponseEntity<?> response = controller.atualizar(1L, dto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("João Atualizado", ((UsuarioEntity) response.getBody()).getNome());
    }

    @Test
    void deletar() {
        doNothing().when(service).deletar(1L);

        ResponseEntity<?> response = controller.deletar(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).deletar(1L);
    }
}
