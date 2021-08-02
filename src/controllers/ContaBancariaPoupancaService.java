package controllers;

import java.util.ArrayList;

import dao.ContaBancariaDAO;
import models.ContaBancaria;
import models.ContaBancariaBonus;
import models.ContaPoupanca;

public class ContaBancariaPoupancaService implements IContaBancariaService {
	
	ContaBancariaDAO contaBancariaDAO;

	public ContaBancariaPoupancaService( ) {
		this.contaBancariaDAO = ContaBancariaDAO.getInstance();
	}
	//está função não está agindo de forma correta
	public boolean renderJuros (double taxa, int numeroIdentificador) {
		ContaBancaria contaBancaria = contaBancariaDAO.procuraPeloId(numeroIdentificador);
		if (!(contaBancaria instanceof ContaPoupanca) || contaBancaria == null) {
			System.out.println("OBJETO NULO OU DIFERENTE DE C. POUPANCA");
			return false;
		}
		double valor = (((ContaPoupanca) contaBancaria).getSaldo()*taxa)/100;
		((ContaPoupanca) contaBancaria).setSaldo(valor+contaBancaria.getSaldo());
		return true;
	}
	
	@Override
	public int cadastrarConta(double saldo, int numeroIdentificador, int tipo) {
//		if (contaBancariaDAO.procuraPeloId(numeroIdentificador) != null) {
//			return -1;
//		}
//		ContaPoupanca contaPoupanca = new ContaPoupanca();
//		contaPoupanca.setNumeroIdentificador(numeroIdentificador);
//		contaPoupanca.setSaldo(saldo);
//		contaBancariaDAO.inserir(contaPoupanca);
		return 0;
	}

	@Override
	public ArrayList<Double> consultarSaldo(int numeroIdentificador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean creditarConta(int numeroIdentificador, Double valor) {
		ContaBancaria contaBancaria = contaBancariaDAO.procuraPeloId(numeroIdentificador);
		if (!(contaBancaria instanceof ContaPoupanca) || contaBancaria == null) {
			return false;
		}
		Double saldo = contaBancaria.getSaldo();
		((ContaPoupanca)contaBancaria).setSaldo(saldo + valor);
		return true;
		}

	@Override
	public boolean creditarContaTransferencia(int numeroIdentificador, Double valor) {
		if (this.contaBancariaDAO.procuraPeloId(numeroIdentificador) == null) {
			return false;
		} else {
			Double saldo = contaBancariaDAO.procuraPeloId(numeroIdentificador).getSaldo();
			contaBancariaDAO.procuraPeloId(numeroIdentificador).setSaldo(saldo + valor);
		}
		return true;
		//return false;
	}

	@Override
	public boolean debitarConta(int numeroIdentificador, Double valor) {
		if (this.contaBancariaDAO.procuraPeloId(numeroIdentificador) == null) {
			return false;
		} else {
			Double saldo = contaBancariaDAO.procuraPeloId(numeroIdentificador).getSaldo();
			contaBancariaDAO.procuraPeloId(numeroIdentificador).setSaldo (saldo - valor);
		}
		return true;
	}

	@Override
	public int transferirConta(int origem, int destino, Double saldo) {
		ContaBancaria contaBancariaOrigem = contaBancariaDAO.procuraPeloId(origem);
		ContaBancaria contaBancariaDestino = contaBancariaDAO.procuraPeloId(destino);
		IContaBancariaService serviceOrigem = getServiceStrategy(contaBancariaOrigem);
		IContaBancariaService serviceDestino = getServiceStrategy(contaBancariaDestino);
		if (serviceOrigem.debitarConta(origem, saldo) == true) {
			boolean response = serviceDestino.creditarContaTransferencia(destino, saldo);
			if (!response) {
				serviceOrigem.creditarConta(origem, saldo);
				return -1;
			} else {
				return 0;
			}
		}
		return -2;
		
	}

	private IContaBancariaService getServiceStrategy(ContaBancaria contaBancaria) {
		if(contaBancaria instanceof ContaBancariaBonus) {
			return new ContaBancariaBonusService();
		}
		if(contaBancaria instanceof ContaPoupanca) {
			return new ContaBancariaPoupancaService();
			
		}
			
		return new ContaBancariaService();
		
	}

}
