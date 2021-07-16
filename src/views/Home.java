package views;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;


public class Home {

	public Home() {
		// TODO Auto-generated constructor stub
	}
	
	public static void telaDespedida(int a) {
		System.out.println("Obrigado por usar Sistema Bancário, até logo!");
	}
	
	public static void telaInicial(int a) {

		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		Scanner input = new Scanner(System.in);
		int opt = -1;

		funcoes.put(0, "sair");
		funcoes.put(1, "Cadastrar Conta");
		funcoes.put(2, "Consultar Saldo");
		funcoes.put(3, "Creditar");
		funcoes.put(4, "Debitar");
		funcoes.put(5, "Transfererir");

		funcoesPtr.put(0, Home::telaDespedida);
		funcoesPtr.put(1, ContaBancariaCLI::telaCadastrar);
		funcoesPtr.put(2, ContaBancariaCLI::telaConsulta);
		funcoesPtr.put(3, Home::telaDespedida);
		funcoesPtr.put(4, Home::telaDespedida);
		funcoesPtr.put(5, Home::telaDespedida);

		 do{
			System.out.println("===== Menu Principal =====");
			System.out.println("\nOperações disponíveis:\n");
			for (int i : funcoes.keySet()) {
				System.out.printf("[%d] %s \n", i, funcoes.get(i));
			}
			try {
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				System.out.println("");
				funcoesPtr.get(opt).accept(1);
			} catch (Exception e) {
				System.out.println("Digite um valor válido");
				opt = -1;

			}
			System.out.println("");
		} while (opt!=0);
	}

}
