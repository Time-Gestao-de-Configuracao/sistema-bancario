package contaBancaria;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

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

}
