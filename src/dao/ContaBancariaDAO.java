package dao;

import java.util.ArrayList;

import models.ContaBancaria;

public final class ContaBancariaDAO {
	
	protected ArrayList<ContaBancaria> contasBancarias;
	protected static ContaBancariaDAO instance;

	public ContaBancariaDAO() {
		this.contasBancarias = new ArrayList<ContaBancaria>();
	}
	
	public static ContaBancariaDAO getInstance() {
		if (instance == null) {
			instance = new ContaBancariaDAO();
		}
		return instance;
	}
	public void inserir(ContaBancaria cb) {
		this.contasBancarias.add(cb);
	}
	
	public int remover(int numeroIdentificador) {
		ContaBancaria aux = new ContaBancaria();
		for (ContaBancaria c: this.contasBancarias) {
			if(c.getNumeroIdentificador() == numeroIdentificador) {
				aux = c;
				break;
			}
		}
		this.contasBancarias.remove(aux);
		return 0;
	}
	
	public int alterar(int numeroIdentificador, ContaBancaria cb) {
		for (ContaBancaria c : this.contasBancarias) {
			if (c.getNumeroIdentificador() == numeroIdentificador) {
				c.setNumeroIdentificador(cb.getNumeroIdentificador());
				c.setSaldo(cb.getSaldo());
				break;
			}
		}
		return 0;
	}
	
	public ContaBancaria procuraPeloId(int numeroIdentificador) {
		for (ContaBancaria c : this.contasBancarias) {
			if (c.getNumeroIdentificador() == numeroIdentificador) {
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<ContaBancaria> procuraTodos(){
		return this.contasBancarias;
	}
}
