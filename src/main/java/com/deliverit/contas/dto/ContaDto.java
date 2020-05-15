package com.deliverit.contas.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ContaDto {

	@NotNull
	@ApiModelProperty(notes = "Descrição da conta", example = "Boleto carro", required = true)
	private String nome;

	@NotNull
	@ApiModelProperty(notes = "Valor da Conta", example = "100.80", required = true)
	private Double valorOriginal;

	@NotNull
	@ApiModelProperty(example = "2020-05-13", required = true)
	private LocalDate dataVencimento;

	@NotNull
	@ApiModelProperty(example = "2020-05-13", required = true)
	private LocalDate dataPagamento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(Double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
