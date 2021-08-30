package contaBancaria;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controllers.ContaBancariaService;

class ContaBancariaTest {
	ContaBancariaService conta = new ContaBancariaService();
	@Test
	void testCadastrarConta() {
		//ContaBancariaService conta = new ContaBancariaService();
		int res = conta.cadastrarConta(10, 1, 1);
		assertEquals(0, res);
	}

	@Test
	void testConsultarSaldo() {
		conta.cadastrarConta(10, 2, 1);
		ArrayList<Double> saldo = conta.consultarSaldo(2);
		boolean result = (!saldo.isEmpty());

	}

	@Test
	void testCreditarConta() {
		conta.cadastrarConta(10, 3, 1);
		ArrayList<Double> saldo = conta.consultarSaldo(3);
		assertEquals(10, saldo.get(0));
	}

	@Test
	void testDebitarConta() {
		conta.cadastrarConta(10, 3, 1);
		conta.debitarConta(3, 5.0);
		ArrayList<Double> saldo = conta.consultarSaldo(3);
		assertEquals(5, saldo.get(0));
	}

}
