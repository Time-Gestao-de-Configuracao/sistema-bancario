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

}
