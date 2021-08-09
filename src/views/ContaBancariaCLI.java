package views;

import java.util.ArrayList;
import java.util.Scanner;

import controllers.ContaBancariaPoupancaService;
import controllers.ContaBancariaService;
import controllers.IContaBancariaService;
import dao.ContaBancariaDAO;

public final class ContaBancariaCLI {

	private static IContaBancariaService contaBancariaService = new ContaBancariaService();
	private static IContaBancariaService contaPoupanca = new ContaBancariaPoupancaService();

	public ContaBancariaCLI() {
		contaBancariaService = new ContaBancariaService();
	}

	public static void telaCadastrar(int a) {
		int numeroIdentificador = 0;
		int tipoConta = 0;
		double saldo = 0.0;
		int aux = -1;
		System.out.println("===== Cadastrar Conta Bancária =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o Número Identificador: ");
				numeroIdentificador = Integer.parseInt(input.nextLine());
				System.out.print("[Int] Entre com o tipo da conta [1] => Conta comum; [2] => Conta Bônus: [3] => Conta Poupanca: ");
				tipoConta = Integer.parseInt(input.nextLine());
				if (tipoConta == 3){
					System.out.print("[Int] Entre com o saldo inicial da conta: ");
					saldo = Integer.parseInt(input.nextLine());
				}

				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		int resultado = contaBancariaService.cadastrarConta(saldo, numeroIdentificador, tipoConta);
		if (resultado != -1) {
			System.out.println("Conta cadastrada com o número " + numeroIdentificador);
		} else {
			System.out.println(
					"Não foi possível cadastrar pois já existe uma conta com esse número: " + numeroIdentificador);
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
				ArrayList<Double> result =  contaBancariaService.consultarSaldo(n);
				System.out.println("Olá, seu saldo é: " + result.get(0));
				if (result.size() > 1) {
					System.out.println("Seu bônus é: " + result.get(1));
				}
				valido = 1;
			} catch (Exception e) {
				System.out.println("Não foi possível encontrar conta com esse ID, digite um parâmetro valido!");
			}
		} while (valido != 1);
	}

	public static void telaCreditar(int n) {
		System.out.println("===== Creditar de uma conta =====");
		int valido = 0;
		Double valor = 0.0;
		do {
			try {
				System.out.print("Digite o identificador: ");
				Scanner input = new Scanner(System.in);
				n = Integer.parseInt(input.nextLine());
				System.out.print("Digite o valor a ser depositado: ");
				valor = Double.parseDouble(input.nextLine());
				valido = 1;

			} catch (Exception e) {
				System.out.println("erro nos parâmetros digitados");
			}

		} while (valido != 1);
		if (!contaBancariaService.creditarConta(n, valor)) {
			System.out.println("Não foi possível encontrar conta com esse ID, digite um parâmetro valido!");
		} else {
			System.out.println("Crédito feito com sucesso");

		}
		while (valido != 1)
			;
	}

	public static void telaDebitar(int n) {
		System.out.println("===== Debitar de uma conta =====");
		int valido = 0;
		Double valor = 0.0;
		do {
			try {
				System.out.print("Digite o identificador: ");
				Scanner input = new Scanner(System.in);
				n = Integer.parseInt(input.nextLine());
				System.out.print("Digite o valor a ser debitado: ");
				valor = Double.parseDouble(input.nextLine());
				valido = 1;

			} catch (Exception e) {
				System.out.println("Erro nos parâmetros digitados.");
			}

		} while (valido != 1);
		if (!contaBancariaService.debitarConta(n, valor)) {
			System.out.println("Não foi possível encontrar conta com esse ID, digite um parâmetro valido!");
		} else {
			System.out.println("Débito realizado com sucesso.");
		}
	}

	public static void telaTransferir(int n) {
		System.out.println("===== Transferência entre contas =====");
		int valido = 0;
		Double valor = 0.0;
		int origem = 0;
		int destino = 0;
		do {
			try {
				System.out.print("Digite o identificador da conta de origem: ");
				Scanner input = new Scanner(System.in);
				origem = Integer.parseInt(input.nextLine());

				System.out.print("Digite o identificador da conta de destino: ");
				destino = Integer.parseInt(input.nextLine());

				System.out.print("Digite o valor a ser debitado: ");
				valor = Double.parseDouble(input.nextLine());
				valido = 1;

			} catch (Exception e) {
				System.out.println("Erro nos parâmetros digitados.");
			}

		} while (valido != 1);
		int response = contaBancariaService.transferirConta(origem, destino, valor);
		switch (response) {
		case 0:
			System.out.println("Transferência realizada com sucesso!!!");
			break;
		case -1:
			System.out.println("Conta de destino não encontrada.");
			System.out.println("Ressarcindo o valor.");
			break;
		case -2:
			System.out.println("Conta de origem não encontrada.");
			break;
		default:
			System.out.println("Algum erro inesperado aconteceu.");
		}
	}
	public static void telaRender(int n) {
		System.out.println("===== Render Juros Conta =====");
		int valido = 0;
		Double valor = 0.0;
		int opc = 0;
		do {
			try {
				System.out.print("Digite o identificador: ");
				Scanner input = new Scanner(System.in);
				opc = Integer.parseInt(input.nextLine());
				System.out.print("Digite o valor da taxa de juros: ");
				valor = Double.parseDouble(input.nextLine());
				valido = 1;

			} catch (Exception e) {
				System.out.println("Erro nos parâmetros digitados.");
			}

		} while (valido != 1);
		if (!contaBancariaService.renderJuros(valor, opc)) {
			System.out.println("Não foi possível encontrar conta com esse ID, digite um parâmetro valido para render juros!");
		} else {
			System.out.println("Juros realizado com sucesso.");
		}
	}
}
