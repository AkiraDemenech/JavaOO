package br.uel.cce.dc.cc.poo.banco;

import java.util.ArrayList;
import java.util.List;

public class Agencia {
	
	private List<Cliente> convencional;
	private List<Cliente> preferencial;
	private int fila;
	private int num;
	private static int ag = 1;
	private static int idadePreferencial = 65;
	
	public Agencia () {
		this(ag);
	}
	public Agencia (int num) {
		this(num, new ArrayList<>(),new ArrayList<>());
	}
	public Agencia (int num, List<Cliente> convencional, List<Cliente> preferencial) {		
		if(num >= ag)
			ag = num + 1;
		this.num = num;
		this.fila = 0;
		this.preferencial = preferencial;
		this.convencional = convencional;		
	}
	public int getNumero () {
		return num;
	}
	public List<Cliente> getConvencional () {
		return this.convencional;
	}
	public List<Cliente> getPreferencial () {
		return this.preferencial;
	}
	public int getIdadePreferencial () {
		return idadePreferencial;
	}
	public static void setIdadePreferencial (int idade) {
		idadePreferencial = idade;
	}
	
	public boolean entrar (Cliente c) {
		if(c.getIdade() >= idadePreferencial) {
			preferencial.add(c);
			return true;
		}
		convencional.add(c);
		return false;
	}
	
	public Cliente atender () {
		if((this.fila < 2 || convencional.size() <= 0) && preferencial.size() > 0) {
			this.fila++;
			return preferencial.remove(0);
		}
		this.fila = 0;
		if(convencional.size() > 0)
			return convencional.remove(0);
		return null;
	}
	
	public Cliente desistir (String nome) {
		Cliente desistente = new Cliente(nome);
		int i = convencional.indexOf(desistente);
		if(i < 0) {
			i = preferencial.indexOf(desistente);
			if(i < 0)
				return null;			
		} else return convencional.remove(i);
		return preferencial.remove(i);
	}
	
	@Override
	public String toString () {
		return "Agência " + this.num + " (" + convencional.size() + " na convencional e " + preferencial.size() + " na preferencial)";
	}
	@Override 
	public boolean equals (Object ag) {
		return ag != null && ag.getClass() == this.getClass() && ((Agencia)ag).num == this.num;
	}

}
