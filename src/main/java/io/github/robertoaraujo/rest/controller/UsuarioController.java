package io.github.robertoaraujo.rest.controller;

import io.github.robertoaraujo.dominio.model.user.UsuarioEntity;
import io.github.robertoaraujo.rest.dto.UsuarioDto;

import io.github.robertoaraujo.service.usuario.UsuarioService;
import io.github.robertoaraujo.utils.JwtUtil;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
@CrossOrigin("http://localhost:4200")
@Api(tags = "Usuários", description = "APIS relacionadas a operações de Usuários")
public class UsuarioController {

    private UsuarioService service;

    private JwtUtil jwtUtil;

    @PostMapping("/cadastrar")
    @ApiOperation(value = "Cadastrar usuário", notes = "Cadastra um usuário")
    public ResponseEntity<UsuarioDto> salvar(@Valid @RequestBody UsuarioDto dto) {
        UsuarioEntity usuarioSalvo = service.salvar(dto);
        UsuarioDto responseDto = new UsuarioDto(usuarioSalvo.getNome(), usuarioSalvo.getEmail(), usuarioSalvo.getSenha());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    @ApiOperation(value = "Listar usuários", notes = "Retorna uma lista de usuários")
    public ResponseEntity<?> listar(
            @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "tamanho", defaultValue = "500") Integer tamanho) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        Page<UsuarioEntity> resultado = service.listar(pageRequest);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }


    @GetMapping("/buscar/{id}")
    @ApiOperation(value = "Buscar usuário", notes = "Retorna um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        UsuarioEntity resultado = service.buscar(id);

        String token = jwtUtil.generateToken(resultado.getEmail());
        UsuarioEntity response = new UsuarioEntity(resultado.getId(),
                resultado.getNome(), resultado.getEmail(), resultado.getSenha(), token, resultado.getEmpenho());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    @ApiOperation(value = "Atualizar usuário", notes = "Atualiza um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody UsuarioDto dto) {
        UsuarioEntity resultado = service.atualizar(id, dto);
        String token = jwtUtil.generateToken(resultado.getEmail());
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    @ApiOperation(value = "Deletar usuário", notes = "Deleta um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
