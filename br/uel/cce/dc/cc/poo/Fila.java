package br.uel.cce.dc.cc.poo;

import br.uel.cce.dc.cc.poo.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fila {
	
	

	
	public static void main (String[] args) {
		final String entrar = 	"entrar";
		final String sair = 	"sair";
		final String desistir =	"desistir";
		final String atender =	"atender";
		final String buscar = 	"buscar";
		final String imprimir =	"imprimir";
		
		List<Cliente> clientes = new ArrayList<>();
		Scanner entrada = new Scanner(System.in);
		String ln, lower;
		do {
			System.out.print(">");
			ln = entrada.nextLine().trim();
			lower = ln.toLowerCase();
			if(lower.startsWith(entrar)) 
				clientes.add(new Cliente(ln.substring(entrar.length())));
			else if(lower.startsWith(desistir))
				System.out.println(clientes.remove(new Cliente(ln.substring(desistir.length()))) ? "Desistiu." : "Cadê? Nem tava na fila antes!");
			else if(lower.startsWith(atender))
				System.out.println("Atendendo\t" + clientes.remove(0));
			else if(lower.startsWith(buscar)) {
				int i = clientes.indexOf(new Cliente(ln.substring(buscar.length())));
				System.out.println((i < 0) ? "Não está na fila" : ("Há " + i + " clientes na frente."));
			} else if(lower.startsWith(imprimir)) {
				if(clientes.size() > 0)
					for(Cliente c: clientes)
						System.out.println(c);
				else System.out.println("A fila está vazia.");
			} else System.out.println("Nenhuma ação foi feita");
		} while (!lower.equals(sair));
		
		entrada.close();
	}
}
