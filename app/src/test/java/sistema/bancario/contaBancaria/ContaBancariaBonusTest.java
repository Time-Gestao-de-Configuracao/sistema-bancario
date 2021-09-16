package sistema.bancario.contaBancaria;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import sistema.bancario.src.controllers.ContaBancariaService;
import sistema.bancario.src.models.ContaBancariaBonus;

class ContaBancariaBonusTest {

//	ContaBancariaService conta = new ContaBancariaService();
	private ContaBancariaService conta;

	@Test
	public void testCreditarConta() {
		conta = new ContaBancariaService();
		conta.cadastrarConta(10, 1, 2);
		conta.creditarConta(1, (double) 90);
		assertEquals(100, conta.consultarSaldo(1).get(0), 1);
	}

	@Test
	public void testTransferirConta() {
		conta = new ContaBancariaService();
		conta.cadastrarConta(50, 99, 2);
		conta.cadastrarConta(50, 100, 2);
		conta.transferirConta(99, 10, (double) 40);
		ArrayList<Double> saldo = conta.consultarSaldo(99);
		assertEquals(10, saldo.get(1));	
	}

	@Test
	public void debitar(){
		conta = new ContaBancariaService();
		conta.cadastrarConta(50, 55, 2);
		conta.debitarConta(55,20.00);

		List<Double> saldoConta = conta.consultarSaldo(55);
		assertEquals(30.00,saldoConta.get(0), 0.00);
	}

	@Test
	public void creditar(){
		conta = new ContaBancariaService();
		conta.cadastrarConta(0, 9, 2);
		conta.creditarConta(9,20.00);

		List<Double> saldoConta = conta.consultarSaldo(9);
		assertEquals(20.00, saldoConta.get(0), 0.00);
	}

	@Test
	void creditarTrasferencia(){
		conta = new ContaBancariaService();
		conta.cadastrarConta(50, 20, 2);
		conta.cadastrarConta(0, 30, 2);

		conta.creditarContaTransferencia(20,20.00);

		ArrayList<Double> bonus = conta.consultarSaldo(20);
		assertEquals(10.00, bonus.get(1),1);
	}



}