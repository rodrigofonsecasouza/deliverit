package com.deliverit.contas.resource;

import java.util.List;
import java.util.function.Consumer;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverit.contas.bean.Conta;
import com.deliverit.contas.dto.ContaDto;
import com.deliverit.contas.repository.ContaRepository;
import com.deliverit.contas.service.ContaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API de Contas")
@RestController
@RequestMapping("conta")
public class ContaResource {

	@Autowired
	private ContaService service;

	@ApiOperation(value = "Retorna todas as contas a pagar", notes = "Esse recurso retorna uma lista de Contas a pagar")
	@GetMapping(produces = "application/json; charset=UTF-8")
	public List<Conta> findAllContas() {
		return service.findAll();
	}

	@ApiOperation(value = "Salvar conta a pagar", response = Conta.class, notes = "Esse recurso salvo o registro inserido e retorna o mesmo")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Conta save(@RequestBody final ContaDto contaDto) {
		return service.save(contaDto);
	}

}
