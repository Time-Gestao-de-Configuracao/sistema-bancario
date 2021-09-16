package sistema.bancario.contaBancaria;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sistema.bancario.src.controllers.ContaBancariaPoupancaService;
import sistema.bancario.src.controllers.ContaBancariaService;
import sistema.bancario.src.controllers.IContaBancariaService;


class ContaBancariaPoupancaTest {
	ContaBancariaService conta = new ContaBancariaService();

	@Test
	void testRenderJuros() {
		conta.cadastrarConta(10, 200, 3);
		conta.renderJuros(10, 200);
		ArrayList<Double> saldo = conta.consultarSaldo(200);
		assertEquals(11.0, saldo.get(0), 1);
		
	}

	@Test
	void testTransferirConta() {
		conta.cadastrarConta(50, 201, 3);
		conta.cadastrarConta(50, 202, 3);
		conta.transferirConta(201, 202, (double) 40);
		ArrayList<Double> saldo = conta.consultarSaldo(201);
		assertEquals(10, saldo.get(0),1);
		
	}

}
