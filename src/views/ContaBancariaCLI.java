package views;

import java.util.Scanner;

import controllers.ContaBancariaService;

public final class ContaBancariaCLI {
	
	private static ContaBancariaService  contaBancariaService = new ContaBancariaService();
	
	public ContaBancariaCLI() {
		contaBancariaService = new ContaBancariaService();
	}
	
	public static void telaCadastrar(int a) {
		int numeroIdentificador = 0;
		double saldo = 0.0;
		int aux = -1;
		System.out.println("===== Cadastrar Conta Bancária =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o Número Identificador: ");
				numeroIdentificador = Integer.parseInt(input.nextLine());
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		int resultado = contaBancariaService.cadastrarConta(saldo, numeroIdentificador);
		if(resultado != -1) {
			System.out.println("Conta cadastrada com o número " + numeroIdentificador);
		} else {
			System.out.println("Não foi possível cadastrar pois já existe uma conta com esse número: " + numeroIdentificador);
		}
		
	}

}
