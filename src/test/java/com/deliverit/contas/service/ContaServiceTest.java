package com.deliverit.contas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.deliverit.contas.bean.Conta;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ContaServiceTest {

	@Autowired
	private ContaService service;

	@Test
	public void deveEntrarRegraATE_3() {
		LocalDate dataPagamento = LocalDate.of(2020, Month.MAY, 12);
		LocalDate dataVencimento = LocalDate.of(2020, Month.MAY, 9);
		Double valorOriginal = 100d;
		Integer diasAtraso = 3;
		Double valorCorrigido = 102.3d;

		Conta conta = new Conta();
		conta.setDataPagamento(dataPagamento);
		conta.setDataVencimento(dataVencimento);
		conta.setValorOriginal(valorOriginal);

		service.analisePagamento(conta);
		assertEquals(conta.getDiasAtraso(), diasAtraso);
		assertEquals(conta.getValorCorrigido(), valorCorrigido);
	}

	@Test
	public void deveEntrarRegraMaior3ATE_5() {
		LocalDate dataPagamento = LocalDate.of(2020, Month.MAY, 12);
		LocalDate dataVencimento = LocalDate.of(2020, Month.MAY, 7);
		Double valorOriginal = 100d;
		Integer diasAtraso = 5;
		Double valorCorrigido = 104d;

		Conta conta = new Conta();
		conta.setDataPagamento(dataPagamento);
		conta.setDataVencimento(dataVencimento);
		conta.setValorOriginal(valorOriginal);

		service.analisePagamento(conta);
		assertEquals(conta.getDiasAtraso(), diasAtraso);
		assertEquals(conta.getValorCorrigido(), valorCorrigido);
	}

	@Test
	public void deveEntrarRegraMaior5() {
		LocalDate dataPagamento = LocalDate.of(2020, Month.MAY, 12);
		LocalDate dataVencimento = LocalDate.of(2020, Month.MAY, 2);
		Double valorOriginal = 100d;
		Integer diasAtraso = 10;
		Double valorCorrigido = 108d;

		Conta conta = new Conta();
		conta.setDataPagamento(dataPagamento);
		conta.setDataVencimento(dataVencimento);
		conta.setValorOriginal(valorOriginal);

		service.analisePagamento(conta);
		assertEquals(conta.getDiasAtraso(), diasAtraso);
		assertEquals(conta.getValorCorrigido(), valorCorrigido);
	}

	@Test
	public void naoDeveCalcularDiasAtraso() {
		LocalDate dataPagamento = LocalDate.of(2020, Month.MAY, 12);
		LocalDate dataVencimento = LocalDate.of(2020, Month.MAY, 12);
		Double valorOriginal = 100d;
		Integer diasAtraso = 0;

		Conta conta = new Conta();
		conta.setDataPagamento(dataPagamento);
		conta.setDataVencimento(dataVencimento);
		conta.setValorOriginal(valorOriginal);

		service.analisePagamento(conta);
		assertEquals(conta.getDiasAtraso(), diasAtraso);
		assertEquals(conta.getValorCorrigido(), valorOriginal);
	}

	@Test
	public void naoDeveCalcularDiasAtrasoComPagamentoAntecipado() {
		LocalDate dataPagamento = LocalDate.of(2020, Month.MAY, 2);
		LocalDate dataVencimento = LocalDate.of(2020, Month.MAY, 12);
		Double valorOriginal = 100d;
		Integer diasAtraso = 0;

		Conta conta = new Conta();
		conta.setDataPagamento(dataPagamento);
		conta.setDataVencimento(dataVencimento);
		conta.setValorOriginal(valorOriginal);

		service.analisePagamento(conta);
		assertEquals(conta.getDiasAtraso(), diasAtraso);
		assertEquals(conta.getValorCorrigido(), valorOriginal);
	}
}
