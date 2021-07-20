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
	
	public boolean creditarConta (int numeroIdentificador, Double saldo) {
		
		if (this.contaBancariaDAO.procuraPeloId(numeroIdentificador) == null || saldo <=0) {
			return false;
		} else {
			Double valor = contaBancariaDAO.procuraPeloId(numeroIdentificador).getSaldo();
			contaBancariaDAO.procuraPeloId(numeroIdentificador).setSaldo(saldo+valor);
		}
		return true;
	}

	public boolean debitarConta (int numeroIdentificador, Double saldo) {

		Double valor = contaBancariaDAO.procuraPeloId(numeroIdentificador).getSaldo();
		if (this.contaBancariaDAO.procuraPeloId(numeroIdentificador) == null || saldo >= valor ) {
			return false;
		} else {
			contaBancariaDAO.procuraPeloId(numeroIdentificador).setSaldo(valor-saldo);
		}
		return true;
	}

	public boolean transferirConta(int origem, int destino, Double saldo) {
		
		if( debitarConta(origem, saldo) == true) {
			creditarConta(destino, saldo);
			return true;
		} 
		return false;
	
	}
}
