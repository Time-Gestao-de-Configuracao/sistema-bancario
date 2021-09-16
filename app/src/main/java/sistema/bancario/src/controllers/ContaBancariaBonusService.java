package sistema.bancario.src.controllers;

import java.util.ArrayList;

import sistema.bancario.src.dao.ContaBancariaDAO;
import sistema.bancario.src.models.ContaBancaria;
import sistema.bancario.src.models.ContaBancariaBonus;

public class ContaBancariaBonusService implements IContaBancariaService{

	ContaBancariaDAO contaBancariaDAO;

	public ContaBancariaBonusService() {
		this.contaBancariaDAO = ContaBancariaDAO.getInstance();
	}

	public boolean creditarConta(int numeroIdentificador, Double valor) {
		ContaBancaria contaBancaria = contaBancariaDAO.procuraPeloId(numeroIdentificador);
		if (!(contaBancaria instanceof ContaBancariaBonus) || contaBancaria == null) {
			return false;
		}
		Double saldo = contaBancaria.getSaldo();
		int bonus = ((ContaBancariaBonus) contaBancaria).getBonus();
		int bonusAdd = (int) (valor / 100);
		contaBancaria.setSaldo(saldo + valor);
		((ContaBancariaBonus) contaBancaria).setBonus(bonus+bonusAdd);
		return true;
	}
	
	public boolean creditarContaTransferencia(int numeroIdentificador, Double valor) {
		ContaBancaria contaBancaria = contaBancariaDAO.procuraPeloId(numeroIdentificador);
		if (!(contaBancaria instanceof ContaBancariaBonus) || contaBancaria == null) {
			return false;
		}
		Double saldo = contaBancaria.getSaldo();
		int bonus = ((ContaBancariaBonus) contaBancaria).getBonus();
		int bonusAdd = (int) (valor / 150);
		contaBancaria.setSaldo(saldo + valor);
		((ContaBancariaBonus) contaBancaria).setBonus(bonus+bonusAdd);
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
	public boolean debitarConta(int numeroIdentificador, Double valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int transferirConta(int origem, int destino, Double saldo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean renderJuros(double taxa, int numeroIdentificador) {
		// TODO Auto-generated method stub
		return false;
	}
}
