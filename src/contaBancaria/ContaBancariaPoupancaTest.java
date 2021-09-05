package contaBancaria;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controllers.ContaBancariaPoupancaService;
import controllers.ContaBancariaService;
import controllers.IContaBancariaService;


class ContaBancariaPoupancaTest {
	ContaBancariaService conta = new ContaBancariaService();
	@Test
	void testRenderJuros() {
		conta.cadastrarConta(10, 1, 3);
		conta.renderJuros(10, 1);
		ArrayList<Double> saldo = conta.consultarSaldo(1);
		assertEquals(11.0, saldo.get(0));
		
	}

	@Test
	void testTransferirConta() {
		conta.cadastrarConta(50, 1, 3);
		conta.cadastrarConta(50, 2, 3);
		conta.transferirConta(1, 2, (double) 40);
		ArrayList<Double> saldo = conta.consultarSaldo(1);
		assertEquals(10, saldo.get(0));
		
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

}
