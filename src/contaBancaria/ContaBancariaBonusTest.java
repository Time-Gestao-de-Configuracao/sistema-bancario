package contaBancaria;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import controllers.ContaBancariaService;
import models.ContaBancariaBonus;

class ContaBancariaBonusTest {

//	ContaBancariaService conta = new ContaBancariaService();
	private ContaBancariaService conta;

	@Before
	public void setUp(){
		this.conta = new ContaBancariaService();
	}

	@After
	public void tearDown() {
		this.conta = null;
	}

	@Test
	void testCreditarConta() {
		conta = new ContaBancariaService();
		conta.cadastrarConta(10, 1, 2);
		conta.creditarConta(1, (double) 90);
		assertEquals(100, conta.consultarSaldo(1).get(0));
	}

	@Test
	void testTransferirConta() {
		conta = new ContaBancariaService();
		conta.cadastrarConta(50, 2, 2);
		conta.cadastrarConta(50, 3, 2);
		conta.transferirConta(2, 3, (double) 40);
		ArrayList<Double> saldo = conta.consultarSaldo(2);
		assertEquals(10, saldo.get(1));	
	}

	@Test
	void debitar(){
		conta = new ContaBancariaService();
		conta.cadastrarConta(50, 2, 2);
		conta.debitarConta(2,20.00);

		List<Double> saldoConta = conta.consultarSaldo(2);
		assertEquals(30.00,saldoConta.get(0), 0.00);
	}

	@Test
	void creditar(){
		conta = new ContaBancariaService();
		conta.cadastrarConta(0, 9, 2);
		conta.creditarConta(9,20.00);

		List<Double> saldoConta = conta.consultarSaldo(9);
		assertEquals(20.00, saldoConta.get(0), 0.00);
	}

//	@Test
//	void creditarTrasferencia(){
//		conta.cadastrarConta(50, 2, 2);
//		conta.cadastrarConta(0, 3, 2);
//
//		conta.creditarContaTransferencia(3,20.00);
//
//		ArrayList<Double> saldo = conta.consultarSaldo(3);
//		assertEquals(50.00, saldo.get(1));
//	}



}