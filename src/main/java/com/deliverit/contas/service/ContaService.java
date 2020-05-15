package com.deliverit.contas.service;

import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverit.contas.bean.Conta;
import com.deliverit.contas.dto.ContaDto;
import com.deliverit.contas.repository.ContaRepository;

@Service
public class ContaService {

	private static final int ATE_3 = 3;
	private static final int ATE_5 = 5;

	@Autowired
	private ContaRepository contaRepository;

	public Conta save(ContaDto contaDto) {
		Conta conta = new Conta(contaDto);
		analisePagamento(conta);
		return contaRepository.save(conta);
	}

	public List<Conta> findAll() {
		return (List<Conta>) contaRepository.findAll();
	}

	/**
	 * Método recebe a conta a ser inserida, verifica as datas de pagamento e
	 * vencimento dependendo dos dias de atraso do pagamento enquadra a conta com a
	 * taxa de juros
	 * 
	 * @param conta
	 */
	public void analisePagamento(Conta conta) {
		Period period = Period.between(conta.getDataVencimento(), conta.getDataPagamento());
		int diasAtraso = period.getDays();
		Double valorOriginal = conta.getValorOriginal();

		if (diasAtraso > 0) {
			conta.setDiasAtraso(diasAtraso);
			if (diasAtraso <= ATE_3) {
				conta.setValorCorrigido(calculaJuros(valorOriginal, 0.02, 0.001, diasAtraso));
			} else if (diasAtraso > ATE_3 && diasAtraso <= ATE_5) {
				conta.setValorCorrigido(calculaJuros(valorOriginal, 0.03, 0.002, diasAtraso));
			} else {
				conta.setValorCorrigido(calculaJuros(valorOriginal, 0.05, 0.003, diasAtraso));
			}
		} else {
			conta.setDiasAtraso(0);
			conta.setValorCorrigido(valorOriginal);
		}
	}

	/**
	 * @param valorOriginal
	 * @param multa
	 * @param jurosDia
	 * @param diasAtraso
	 * @return Double sum (valorOriginal, multa, jurosDia)
	 */
	private Double calculaJuros(Double valorOriginal, Double multa, Double jurosDia, int diasAtraso) {
		multa = valorOriginal * multa;
		jurosDia = valorOriginal * (jurosDia * diasAtraso);

		return valorOriginal + multa + jurosDia;
	}
}
