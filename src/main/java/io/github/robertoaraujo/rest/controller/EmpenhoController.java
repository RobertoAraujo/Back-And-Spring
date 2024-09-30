package io.github.robertoaraujo.rest.controller;

import io.github.robertoaraujo.dominio.model.empenho.EmpenhoEntity;
import io.github.robertoaraujo.dominio.model.user.UsuarioEntity;
import io.github.robertoaraujo.rest.dto.EmpenhoDto;
import io.github.robertoaraujo.service.empenho.EmpenhoService;
import io.github.robertoaraujo.service.usuario.UsuarioService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/empenhos")
@Api(tags = "Empenhos", description = "APIS relacionadas a operações de Empenhos")
public class EmpenhoController {

    @Autowired
    private EmpenhoService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<EmpenhoDto> salvar(@RequestBody @Valid EmpenhoDto dto) {
        EmpenhoEntity empenho = service.salvar(dto);

        EmpenhoDto responseDto = new EmpenhoDto(
                empenho.getNumeroEmpenho(),
                empenho.getDataEmpenho(),
                empenho.getDataVencimento(),
                empenho.getValorEmpenho(),
                empenho.getUsuario().getId());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(
            @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "tamanho", defaultValue = "500") Integer tamanho) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        Page<EmpenhoEntity> resultado = service.listar(pageRequest);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
