package com.deliverit.contas.bean;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.deliverit.contas.dto.ContaDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Contas a pagar")
@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@NotNull
	@Column(name = "valorOriginal", nullable = false)
	@ApiModelProperty(notes = "Valor da Conta", example = "100.80")
	private Double valorOriginal;

	@NotNull
	@Column(name = "valorCorrigido", nullable = false)
	@ApiModelProperty(readOnly = true)
	private Double valorCorrigido;

	@NotNull
	@Column(name = "dataVencimento", nullable = false)
	@ApiModelProperty(example = "2020-05-13", required = true)
	private LocalDate dataVencimento;

	@NotNull
	@Column(name = "dataPagamento", nullable = false)
	@ApiModelProperty(example = "2020-05-13", required = true)
	private LocalDate dataPagamento;

	@NotNull
	@Column(name = "diasAtraso", nullable = false)
	@ApiModelProperty(readOnly = true)
	private Integer diasAtraso;
	
	public Conta() {
		
	}

	public Conta(ContaDto contaDto) {
		this.dataPagamento = contaDto.getDataPagamento();
		this.dataVencimento = contaDto.getDataVencimento();
		this.valorOriginal = contaDto.getValorOriginal();
		this.nome = contaDto.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Double getValorCorrigido() {
		return valorCorrigido;
	}

	public void setValorCorrigido(Double valorCorrigido) {
		this.valorCorrigido = valorCorrigido;
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

	public Integer getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(Integer diasAtraso) {
		this.diasAtraso = diasAtraso;
	}
}
