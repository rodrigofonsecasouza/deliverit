package com.deliverit.contas.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.deliverit.contas.bean.Conta;
import com.deliverit.contas.dto.ContaDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ContaResourceTest {

	@Autowired
	private ContaResource resource;

	@Test
	public void deveGravarConta() {
		String nome = "conta teste";
		LocalDate dataPagamento = LocalDate.of(2020, Month.MAY, 12);
		LocalDate dataVencimento = LocalDate.of(2020, Month.MAY, 9);
		Double valorOriginal = 100d;

		ContaDto contaDto = new ContaDto();
		contaDto.setNome(nome);
		contaDto.setDataPagamento(dataPagamento);
		contaDto.setDataVencimento(dataVencimento);
		contaDto.setValorOriginal(valorOriginal);
		Conta conta = resource.save(contaDto);
		assertNotNull(conta.getId());

	}

	@Test
	public void deveBuscarTodasContas() {
		List<Conta> contas = resource.findAllContas();
		assertEquals(contas.size(), 1);
	}

}
