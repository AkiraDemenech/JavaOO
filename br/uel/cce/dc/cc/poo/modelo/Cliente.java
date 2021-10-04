package br.uel.cce.dc.cc.poo.modelo;

import java.time.LocalTime;

public class Cliente {
	
	public Cliente () {
		this(null);
	}
	public Cliente (String nome) {
		this(nome,0);
	}
	public Cliente (String nome, int idade) {
		this(nome, idade, LocalTime.now());
	}
	public Cliente (String nome, int idade, int hora, int min) {
		setNome(nome);
		setIdade(idade);		
		setChegada(hora,min);
	}
	public Cliente (String nome, int idade, LocalTime chegada) {
		this(nome,idade,chegada.getHour(),chegada.getMinute());
	}
	public int getHoraChegada () {
		return this.hora;
	}
	public int getMinChegada () {
		return this.min;
	}
	public void setChegada (LocalTime chegada) {
		setChegada(chegada.getHour(), chegada.getMinute());
	}
	public void setChegada (int hora, int min) {
		this.hora = hora;
		this.min = min;
	}
	public int getIdade () {
		return this.idade;
	}
	public void setIdade (int idade) {
		this.idade = idade;
	}
	public void setNome (String nome) {
		if(nome != null)
			nome = nome.trim().toUpperCase();
		this.nome = nome;

	}	
	public String getNome () {
		return this.nome;
	}
		
	public String getServico () {
		return servico;
	}
	public void setServico (String servico) {
		this.servico = servico;
	}
	
	private String servico = null;
	private String nome;
	private int idade;
	private int hora;
	private int min;
	
	@Override
	public String toString () {
		return nome + '\t' + idade + " anos\t" + servico + "\t[" + hora + ":" + min + "]";
	}

	@Override
	public int hashCode() {
		return (this.nome == null) ? 0 : this.nome.hashCode();
	}
	 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		String nome;
		if (obj.getClass() != this.getClass()) {
			if(obj.getClass() == String.class)
				nome = ((String) obj).toUpperCase().trim();
			else return false;
		} else {
			nome = ((Cliente) obj).getNome();
		}
		
		if (this.nome != nome) {
			if (this.nome == null || nome == null)
				return false;
		} else return true; 
		return this.nome.equals(nome);
	}		
}
