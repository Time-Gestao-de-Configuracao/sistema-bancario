package controllers;

import java.util.ArrayList;

public interface IContaBancariaService {

	int cadastrarConta(double saldo, int numeroIdentificador, int tipo);

	ArrayList<Double> consultarSaldo(int numeroIdentificador);

	boolean creditarConta(int numeroIdentificador, Double valor);

	boolean creditarContaTransferencia(int numeroIdentificador, Double valor);

	boolean debitarConta(int numeroIdentificador, Double valor);

	int transferirConta(int origem, int destino, Double saldo);
	
	public boolean renderJuros (double taxa, int numeroIdentificador);

}