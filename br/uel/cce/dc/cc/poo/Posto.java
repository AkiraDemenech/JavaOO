package br.uel.cce.dc.cc.poo;

import br.uel.cce.dc.cc.poo.posto.Combustivel;
import br.uel.cce.dc.cc.poo.posto.Bomba;

import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Posto {

	
	
	
	public static void main(String[] args) {
		
		
		
		final String SAIR = "sair".toUpperCase();
		final String NOVA = "nova".toUpperCase();
		final String TUDO = "tudo".toUpperCase();
		final String VER = "ver".toUpperCase();
		
		final String BOMBA = "bomba".toUpperCase();
		final String ENCHE = "enche".toUpperCase();
		final String PAGAR = "pagar".toUpperCase();
		final String PRECO = "preço".toUpperCase();
		final String COMBUSTIVEL = "abastecer".toUpperCase();
		
		final String AJUDA = "ajuda".toUpperCase();
		
		System.out.println("Escreva " + AJUDA + " para rever a lista de comandos.");
		
		
		
		String in = AJUDA;
		Scanner sc = new Scanner(System.in);
		Combustivel com;
		List<Bomba> bombas = new ArrayList<>();
		Bomba bi = null;
		int b, i;
		while(!in.startsWith(SAIR)) {			
			
			if(in.startsWith(VER)) {
				if(in.contains(TUDO)) {
					System.out.print(bombas.size() + " bomba");
					if(bombas.size() != 1) 
						System.out.print('s');
					if(bombas.size() > 0)
						System.out.print(':');	
					
					System.out.print('\n');
					for(Bomba bom : bombas)
						System.out.println(bom);
				} else System.out.println(bi);
			
			} else if(in.startsWith(AJUDA))
				System.out.println("Escreva:\n" +
						'\t' + SAIR + " para sair\n" +
						'\t' + VER + " para tabela de preços da bomba atual, e " + VER + '_' + TUDO + " para as de todas as bombas.\n" +
						'\t' + BOMBA + " e o identificador numérico para selecionar a bomba identificada ou " + NOVA + " para criar uma nova bomba com identificador incrementado.\n" + 
						'\t' + COMBUSTIVEL + " e o nome, adicionando ainda:\n\t\t" +
						PRECO + " e o valor para registrar o preço desse combustível na bomba atual.\n\t\t" +
						PAGAR + " e o valor para ver quantos litros poderá comprar.\n\t\t" + 
						ENCHE + " e a quantidade de litros para ver qual o valor da compra.\n");
			else if(in.startsWith(COMBUSTIVEL)) {
				if(bi == null) {
					bi = new Bomba();
					bombas.add(bi);
					System.out.println(bi + "\tadicionada automaticamente.");
				}
				
				com = new Combustivel(sc.next());
				i = bi.getCombustiveis().indexOf(com);
				if(i < 0) {
					i = bi.getCombustiveis().size();
					bi.getCombustiveis().add(com);
					System.out.println("adicionado em " + i);
				} else com = bi.getCombustiveis().get(i);
				
				in = sc.next().toUpperCase().trim();
				try {
					if(in.startsWith(PRECO)) 
						com.setCusto(sc.nextFloat());
					else if(in.startsWith(PAGAR))
						System.out.println(com.litros(sc.nextFloat()) + " litros.");
					else if(in.startsWith(ENCHE))
						System.out.println(Combustivel.getMoeda() + com.valor(sc.nextFloat()));					
				} catch (NumberFormatException | InputMismatchException e) {
					System.out.println("Falha na leitura");
				}
				System.out.println(com);
																
			} else if(in.startsWith(BOMBA)) {  
				try {
					in = sc.next().trim().toUpperCase();
					bi = new Bomba(Integer.parseInt(in));										
				} catch (NumberFormatException num) {
					if(in.startsWith(NOVA))
						bi = new Bomba();
					else {
						System.out.println("ID errado.");
						continue;
					}
				}
				i = bombas.indexOf(bi);
 				if(i < 0) {
					i = bombas.size();
 					bombas.add(bi);
 					System.out.println("adicionada em " + i);
 				} else bi = bombas.get(i); 				
				System.out.println(bi);
			}
			in = sc.next().toUpperCase();
		} 
		sc.close();

	}

}
