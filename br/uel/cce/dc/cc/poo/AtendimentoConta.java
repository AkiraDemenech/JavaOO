package br.uel.cce.dc.cc.poo;

import br.uel.cce.dc.cc.poo.banco.conta.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AtendimentoConta {
	
	

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);				
		
		final String sair	= "sair";
		final String entrar	= "conta";		
		final String sacar	= "sacar";
		final String ajuda	= "ajuda";
		final String saldo	= "saldo";
		final String render	= "render";
		final String depositar	= "depositar";
		final String listar = "listar";
		
		System.out.println("Digite " + ajuda.toUpperCase() + " (não é case sensitive)");
		
				
		String entrada;		
		ContaBancaria atual = null;
		List<ContaBancaria> contas = new LinkedList<>();
		do {
			System.out.print("$");
			entrada = s.next().toLowerCase();
			
			if(entrada.equals(entrar)) {
				System.out.println("Informe o número:");
				atual = new ContaBancaria("", s.next());
				int i = contas.indexOf(atual);
				if(i < 0) {
					System.out.println("Informe cliente:");
					while(atual.getCliente().length() == 0)
						atual.setCliente(s.nextLine());
					
					System.out.println("Conta Corrente (C) ou Poupança (P)?");
					boolean b;
					do {
						b = false;
						switch(s.next().toUpperCase().charAt(0)) {
							case 'C':
								ContaCorrente c = new ContaCorrente(atual);
								System.out.print("Informe o limite:");
								c.setLimite(s.nextBigDecimal());
							//	contas.add(c);
								atual = (ContaBancaria) c;
								break;
							
							case 'P':
								ContaPoupanca p = new ContaPoupanca(atual);
								System.out.print("Informe o dia de rendimento:");
								p.setDiaRendimento(s.nextInt());
								atual = (ContaBancaria) p;
							//	contas.add(p);
								break;
								
							case 'E':
								atual = null;
								break;
								
							default:
								System.out.println("Entrada não reconhecida, digite novamente (ou encerre o processo de criação de conta com E).");
								b = true;
						}
					} while(b);																	
					if(atual == null) 
						System.out.println("A conta não foi criada.");						
					else contas.add(atual);
				} else atual = contas.get(i);
				System.out.println(" CONTA ATUAL:");
				entrada = saldo;
			} else if(entrada.equals(sacar)) {				
				if(atual == null)
					System.out.println("Selecione ou crie uma conta para poder tentar sacar.");
				else {
					System.out.println("Quantia:");
					if(atual.sacar(s.nextBigDecimal())) {
						System.out.println("Saque realizado!");
						entrada = saldo;
					} else System.out.println("Não foi possível realizar o saque.");
				}				
			} else if(entrada.equals(render)) {
				if(atual == null || atual.getClass() != ContaPoupanca.class)
					System.out.println("Selecione ou crie uma conta poupança para render.");
				else {
					System.out.println("Insira a taxa percentual de rendimento:");
					((ContaPoupanca)atual).calcularNovoSaldo(s.nextBigDecimal());
					entrada = saldo;
				}
			} else if(entrada.equals(depositar)) {
				if(atual == null) 
					System.out.println("Selecione ou crie uma conta para depositar.");
				else {
					System.out.println("Quantia:");
					atual.depositar(s.nextBigDecimal());
					entrada = saldo;
				}
			} 
			if(entrada.equals(ajuda)) {
				System.out.println(entrar.toUpperCase() + ", seguido do número da conta (depois fornecendo eventuais informações pedidas), cria uma nova conta ou seleciona uma já existente.\n" + 
									depositar.toUpperCase() + ", seguido do valor depositado, deposita o valor informado na conta atual.\n" + 
									sacar.toUpperCase() + ", seguido do valor, tenta fazer o saque e avisa se não foi possível.\n" + 
									render.toUpperCase() + ", seguido da taxa, atualiza o saldo (se a conta atual for poupança).\n" + 
									saldo.toUpperCase() + " exibe o saldo (e demais informações) da conta atual.\n" + 
									listar.toUpperCase() + " exibe todas as contas já cadastradas.\n" + 
									sair.toUpperCase() + " fecha o programa.");
			} else if(entrada.equals(listar)) {
				System.out.println(contas.size() + " conta" + ((contas.size() > 1)?("s:"):((contas.size() <= 0)?('s'):(':'))));
				for(ContaBancaria b: contas)
					System.out.println(b);
			} else if(entrada.equals(saldo)) 
				System.out.println(atual);
			
		} while(!entrada.equals(sair)); //*/
		
		s.close();
	}

}
