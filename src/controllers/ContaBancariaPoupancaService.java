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
	
	public boolean renderJuros (double taxa, int numeroIdentificador) {
		ContaBancaria contaBancaria = contaBancariaDAO.procuraPeloId(numeroIdentificador);
		if (!(contaBancaria instanceof ContaPoupanca) || contaBancaria == null) {
			return false;
		}
		Double valor = contaBancaria.getSaldo();
		Double novo_valor = (valor*taxa)/100;
		contaBancaria.setSaldo(novo_valor);
		return true;
	}
	
	@Override
	public int cadastrarConta(double saldo, int numeroIdentificador, int tipo) {
		// TODO Auto-generated method stub
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
		contaBancaria.setSaldo(valor);
		return true;
		//return false;
	}

	@Override
	public boolean creditarContaTransferencia(int numeroIdentificador, Double valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean debitarConta(int numeroIdentificador, Double valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int transferirConta(int origem, int destino, Double saldo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
