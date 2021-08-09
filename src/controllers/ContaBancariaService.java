package controllers;

import java.util.ArrayList;

import dao.ContaBancariaDAO;
import models.ContaBancaria;
import models.ContaBancariaBonus;
import models.ContaPoupanca;

public class ContaBancariaService implements IContaBancariaService {

	ContaBancariaDAO contaBancariaDAO;

	public ContaBancariaService() {
		this.contaBancariaDAO = ContaBancariaDAO.getInstance();
	}

	@Override
	public int cadastrarConta(double saldo, int numeroIdentificador, int tipo) {
		if (this.contaBancariaDAO.procuraPeloId(numeroIdentificador) != null) {
			return -1;
		} else {
			ContaBancaria novaConta;
			switch (tipo) {
			case 1:
				novaConta = new ContaBancaria();
				break;
			case 2:
				novaConta = new ContaBancariaBonus();
				break;
			case 3:
				novaConta = new ContaPoupanca();
				break;
			default:
				return -1;
			}
			novaConta.setNumeroIdentificador(numeroIdentificador);
			novaConta.setSaldo(saldo);
			this.contaBancariaDAO.inserir(novaConta);

		}
		return 0;
	}

	@Override
	public ArrayList<Double> consultarSaldo(int numeroIdentificador) {
		ContaBancaria contaBancaria = contaBancariaDAO.procuraPeloId(numeroIdentificador);
		ArrayList<Double> result = new ArrayList<Double>();
		result.add(contaBancaria.getSaldo());
		if (contaBancaria instanceof ContaBancariaBonus) {
			result.add((double) ((ContaBancariaBonus) contaBancaria).getBonus());
		}
		return result;
	}

	@Override
	public boolean creditarConta(int numeroIdentificador, Double valor) {
		ContaBancaria contaBancaria = contaBancariaDAO.procuraPeloId(numeroIdentificador);
		IContaBancariaService serviceConta= getServiceStrategy(contaBancaria);
		if (contaBancaria == null) {
			return false;
		} else {
			if(!(serviceConta instanceof ContaBancariaService)) {
				serviceConta.creditarConta(numeroIdentificador, valor);
			} else {
				Double saldo = contaBancaria.getSaldo();
				contaBancaria.setSaldo(saldo + valor);
			}
		}
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
	}

	@Override
	public boolean debitarConta(int numeroIdentificador, Double valor) {
		if (this.contaBancariaDAO.procuraPeloId(numeroIdentificador) == null) {
			return false;
		} else {
			Double saldo = contaBancariaDAO.procuraPeloId(numeroIdentificador).getSaldo();
			ContaBancaria contaBancaria = contaBancariaDAO.procuraPeloId(numeroIdentificador);
			if ((contaBancaria instanceof ContaPoupanca)) {
				if (saldo - valor <= 1000) {
					return false;
				}
			}
			contaBancariaDAO.procuraPeloId(numeroIdentificador).setSaldo (saldo - valor);
		}
		return true;
	}

	@Override
	public int transferirConta(int origem, int destino, Double saldo) {
		ContaBancaria contaBancariaOrigem = contaBancariaDAO.procuraPeloId(origem);
		ContaBancaria contaBancariaDestino = contaBancariaDAO.procuraPeloId(destino);
		IContaBancariaService serviceOrigem = this.getServiceStrategy(contaBancariaOrigem);
		IContaBancariaService serviceDestino = this.getServiceStrategy(contaBancariaDestino);
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
	
	public IContaBancariaService getServiceStrategy(ContaBancaria contaBancaria) {
		if(contaBancaria instanceof ContaBancariaBonus) {
			return new ContaBancariaBonusService();
		} else {
			if(contaBancaria instanceof ContaPoupanca) {
			return new ContaBancariaPoupancaService();
			
			}
			return new ContaBancariaService();
		}
		
	}

	@Override
	public boolean renderJuros(double taxa, int numeroIdentificador) {
		ContaBancaria contaBancaria = contaBancariaDAO.procuraPeloId(numeroIdentificador);
		if (!(contaBancaria instanceof ContaPoupanca) || contaBancaria == null) {
			return false;
		}
		float valor = (float) ((((ContaPoupanca) contaBancaria).getSaldo()*taxa)/100);
		((ContaPoupanca) contaBancaria).setSaldo(valor+contaBancaria.getSaldo());
		return true;
		
	}
}
