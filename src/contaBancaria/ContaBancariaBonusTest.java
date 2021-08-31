package contaBancaria;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controllers.ContaBancariaService;
import models.ContaBancariaBonus;

class ContaBancariaBonusTest {
	ContaBancariaService conta = new ContaBancariaService();
	

	@Test
	void testCreditarConta() {
		conta.cadastrarConta(10, 1, 2);
		conta.creditarConta(1, (double) 90);
		assertEquals(100, conta.consultarSaldo(1).get(0));
	}

	@Test
	void testTransferirConta() {
		conta.cadastrarConta(50, 2, 2);
		conta.cadastrarConta(50, 3, 2);
		conta.transferirConta(2, 3, (double) 40);
		ArrayList<Double> saldo = conta.consultarSaldo(2);
		assertEquals(10, saldo.get(1));	
	}

}