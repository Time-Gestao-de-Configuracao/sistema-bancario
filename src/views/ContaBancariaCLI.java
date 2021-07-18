package views;

import java.util.Scanner;

import controllers.ContaBancariaService;
import dao.ContaBancariaDAO;

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
	public static void telaConsulta(int n) {
		System.out.println("===== Consultar saldo da Conta Bancária =====");
		int valido = 0;
		do {
			System.out.println("Digite seu Identificador da conta: ");
			try {
				Scanner input = new Scanner(System.in);
				n = Integer.parseInt(input.nextLine());
				System.out.println("Olá, seu saldo é: " + contaBancariaService.consultarSaldo(n));
				valido = 1;
			} catch (Exception e) {
				System.out.println ("Não foi possível encontrar conta com esse ID, digite um parâmetro valido!");
			}
		}  while (valido != 1);
		
	}
	public static void telaCreditar (int n) {
		System.out.println ("Adicionando valor a conta");
		int valido = 0;
		Double valor = 0.0;
		do {
			try {
				System.out.println ("Digite o identificador");
				Scanner input = new Scanner(System.in);
				n = Integer.parseInt(input.nextLine());
				System.out.println("Digite o valor a ser depositado:");
				valor = Double.parseDouble(input.nextLine());
				valido = 1;

			} catch (Exception e) {
				System.out.println ("erro nos parâmetros digitados");
			}
<<<<<<< HEAD

		} while (valido != 1);
		if (!contaBancariaService.creditarConta(n, valor)) {
			System.out.println("valores informados incorretos");
		} else {
			System.out.println("Crédito feito com sucesso");

		
=======
>>>>>>> a25b37980d6c39b8119bc88a25f3fe209d2a8d61
			
		} while (valido != 1);

	}

	public static void telaDebitar (int n) {
		System.out.println ("Debitando valor da conta");
		int valido = 0;
		Double valor = 0.0;
		do {
			try {
				System.out.println ("Digite o identificador");
				Scanner input = new Scanner(System.in);
				n = Integer.parseInt(input.nextLine());
				System.out.println("Digite o valor a ser debitado:");
				valor = Double.parseDouble(input.nextLine());
				valido = 1;

			} catch (Exception e) {
				System.out.println ("erro nos parâmetros digitados");
			}
			
		} while (valido != 1);
		if (!contaBancariaService.debitarConta(n, valor)) {
			System.out.println("valores informados incorretos");
		} else {
			System.out.println("Débito feito com sucesso");

		}
		
	}
	
}
