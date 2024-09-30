package io.github.robertoaraujo.rest.controller;

import io.github.robertoaraujo.dominio.model.empenho.EmpenhoEntity;
import io.github.robertoaraujo.dominio.model.liquidacao.LiquidacaoEntity;
import io.github.robertoaraujo.rest.dto.LiquidacaoDto;
import io.github.robertoaraujo.service.empenho.EmpenhoService;
import io.github.robertoaraujo.service.liquidacao.LiquidacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/Licitacoes")
@Api(tags = "Liquidacoes", description = "APIS relacionadas a operações de Liquidações")
public class LiquidacaoController {

    @Autowired
    private LiquidacaoService service;


    @PostMapping("/cadastrar-liquidacao")
    @ApiOperation(value = "Cadastrar liquidação", notes = "Cadastra uma liquidação")
    public ResponseEntity<?> salvaLiquidacao(@RequestBody @Valid LiquidacaoDto dto) {
//       LiquidacaoEntity liquidacao = service.salvar(dto);
//        LiquidacaoDto responseDto = new LiquidacaoDto(
//               liquidacao.getDataLiquidacao(),
//               liquidacao.getValorLiquidacao());


        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/listar-liquidacoes")
    @ApiOperation(value = "Listar liquidações", notes = "Retorna uma lista de liquidações")
    public ResponseEntity<?> listarLiquidacoes(
            @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "tamanho", defaultValue = "500") Integer tamanho) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);

        return ResponseEntity.ok(service.listar(pageRequest));
    }
}
