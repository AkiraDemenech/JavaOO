package br.uel.cce.dc.cc.poo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.uel.cce.dc.cc.poo.banco.Agencia;
import br.uel.cce.dc.cc.poo.modelo.Cliente;


public class Banco {
	
	public final static String entrar = 	"entrar";
	public final static String sair = 	"sair";
	public final static String nova = 	"nova";
	public final static String ajuda = 	"ajuda";
	public final static String desistir =	"desistir";
	public final static String atender =	"atender";
	public final static String buscar = 	"buscar";
	public final static String imprimir =	"imprimir";
	public final static String agencia =	"agência";
	
	public Banco () {
		this(new ArrayList<>());
	}
	public Banco (List<Agencia> agencias) {
		bancos = agencias;
	}
	
	private List<Agencia> bancos;
	private Agencia ag = null;
	
	public Agencia getAgAtual () {
		if(ag != null)
			return ag;
		setAgAtual(new Agencia());
		System.out.println(ag + " criada automaticamente.");
		return ag;
	}
	
	public void setAgAtual (Agencia atual) {
		int i = bancos.indexOf(atual);
		if(i < 0) {
			ag = atual;
			bancos.add(atual);
		} else ag = bancos.get(i);
	}

	
	public static void main (String[] args) {
		System.out.println("Digite " + ajuda.toUpperCase() + " (não é case sensitive)");
		
		
	//	Agencia atual = null;
	//	List<Agencia> bancos = new ArrayList<>();
		Cliente novo;
		Banco atual = new Banco();
		Scanner entrada = new Scanner(System.in);
		String in;
		int i;
		do {
			System.out.print("$");
			in = entrada.next().toLowerCase();
			
			if(in.startsWith(agencia)) {
				in = entrada.next().toLowerCase();
				try {
					atual.setAgAtual(new Agencia(Integer.parseInt(in)));
				} catch (NumberFormatException num) {
					if(in.startsWith(nova))// || atual.getAgAtual() == null)
						atual.setAgAtual(new Agencia());					
					else System.out.println("Não foi criada nenhuma nova agência.");						
				}				
				System.out.println("Estamos gerenciando a " + atual.ag);
			} //else if(atual.getAgAtual() == null)	System.out.println("Atenção: ainda não foi criada nenhuma agência.");
			if(in.startsWith(entrar)) {
				novo = new Cliente(entrada.nextLine());				
				
				System.out.print("Serviço:");
				novo.setServico(entrada.nextLine());								
				
				System.out.print("Idade:");
				novo.setIdade(entrada.nextInt());
				
				System.out.println("Direcionando cliente para a fila " + (atual.getAgAtual().entrar(novo) ? "preferencial" : "convencional") + "....");
				
				System.out.print("Horário (horas e minutos) em que chegou:");
				novo.setChegada(entrada.nextInt(), entrada.nextInt());				
				
				System.out.println(novo + " agora está na fila.");								
			} else if(in.startsWith(desistir)) {
				in = entrada.nextLine();
				novo = atual.getAgAtual().desistir(in);
				if(novo == null)
					System.out.println("Não há cliente com o nome \"" + in.toUpperCase().trim() + "\" em nenhuma fila, então ninguém desistiu (ainda).");
				else System.out.println(novo + " desitiu da fila.");				
			} else if(in.startsWith(buscar)) {
				novo = new Cliente(entrada.nextLine());
				i = atual.getAgAtual().getPreferencial().indexOf(novo);
				if(i >= 0) {
					System.out.print("Na preferencial, ");									
					novo = atual.getAgAtual().getPreferencial().get(i);
				} else {
					i = atual.getAgAtual().getConvencional().indexOf(novo);				
					if(i >= 0) {
						System.out.print("Convencional: ");											
						novo = atual.getAgAtual().getConvencional().get(i);
					}						 
				}
				if(i < 0)
					System.out.println(novo.getNome() + " não está em nenhuma fila desta agência");
				else {
					if(i == 0)
						System.out.println("em primeiro lugar.");
					else System.out.println("atrás de " + i + " cliente" + (i > 1 ? 's' : '.'));					
					System.out.println(novo);
				}
			} else if(in.startsWith(imprimir)) {
				for(Agencia a: atual.bancos)
					if(a != atual.getAgAtual())
						System.out.println(a + ";");
				System.out.println(atual.getAgAtual() + ":");
				
				System.out.println("\t" + atual.getAgAtual().getPreferencial().size() + " cliente" + (atual.getAgAtual().getPreferencial().size() != 1 ? 's' : ' ') + " " + atual.getAgAtual().getIdadePreferencial() + "+ na fila prioritária" + (atual.getAgAtual().getPreferencial().size() > 0 ? ':' : ' '));
				for(Cliente c: atual.getAgAtual().getPreferencial())
					System.out.println(c);
				
				System.out.println("\t" + atual.getAgAtual().getConvencional().size() + " cliente" + (atual.getAgAtual().getConvencional().size() != 1 ? 's' : ' ') + " na fila convencional" + (atual.getAgAtual().getConvencional().size() > 0 ? ':' : ' '));
				for(Cliente c: atual.getAgAtual().getConvencional())
					System.out.println(c);
			} else if(in.startsWith(atender)) {
				novo = atual.getAgAtual().atender();
				if(novo == null)
					System.out.println("Não há clientes para atender.");
				else System.out.println("Atendendo " + novo);
			} 
			if(in.contains(ajuda)) 
				System.out.println(entrar.toUpperCase() + ", seguido do nome completo (depois fornecendo as informações pedidas), adiciona mais uma pessoa à fila adequada da agência atual.\n" + 
									buscar.toUpperCase() + ", seguido do nome completo, procura a pessoa nas duas filas e retorna a primeira ocorrência.\n" + 
									desistir.toUpperCase() + ", seguido do nome completo, remove a primeira ocorrência da fila em que estiver.\n" + 
									atender.toUpperCase() + " retira a primeira pessoa da fila adequada.\n" + 
									imprimir.toUpperCase() + " exibe todas as pessoas das filas da agência atual depois de ter listado todas as agências.\n" + 
									agencia.toUpperCase() + ", seguido do número ou de " + nova.toUpperCase() + ", cria uma nova agência.\n" + 
									sair.toUpperCase() + " fecha o programa.");
				
			
		} while (!in.equals(sair));
		
		entrada.close();
	}
}
