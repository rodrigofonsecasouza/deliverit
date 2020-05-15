package com.deliverit.contas.repository;

import org.springframework.data.repository.CrudRepository;

import com.deliverit.contas.bean.Conta;

public interface ContaRepository extends CrudRepository<Conta, Long>{

}
