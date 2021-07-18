package controllers;

import dao.ContaBancariaDAO;
import models.ContaBancaria;

public class ContaBancariaService {
	
	ContaBancariaDAO contaBancariaDAO;
	
	public ContaBancariaService() {
		this.contaBancariaDAO = ContaBancariaDAO.getInstance();
	}
	
	public int cadastrarConta(double saldo, int numeroIdentificador) {
		if (this.contaBancariaDAO.procuraPeloId(numeroIdentificador) != null) {
			return -1;
		} else {
			ContaBancaria novaConta = new ContaBancaria();
			novaConta.setNumeroIdentificador(numeroIdentificador);
			novaConta.setSaldo(saldo);
			this.contaBancariaDAO.inserir(novaConta);
		}
		return 0;
	}
	
	public Double consultarSaldo (int numeroIdentificador) {
				
		return contaBancariaDAO.procuraPeloId(numeroIdentificador).getSaldo();
	}
	
	public void creditarConta (int numeroIdentificador, Double saldo) {
		contaBancariaDAO.procuraPeloId(numeroIdentificador).setSaldo(saldo);
	}

	public boolean debitarConta (int numeroIdentificador, Double saldo) {
		//
		Double valor = contaBancariaDAO.procuraPeloId(numeroIdentificador).getSaldo();
		if (this.contaBancariaDAO.procuraPeloId(numeroIdentificador) == null || saldo >= valor ) {
			return false;
		} else {
			contaBancariaDAO.procuraPeloId(numeroIdentificador).setSaldo(valor-saldo);
		}
		return true;
	}
	
}
