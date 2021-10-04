package br.uel.cce.dc.cc.poo.posto;

public class Combustivel {
	
	private static String moeda = "R$";
	
	private String nome;
	private float litro;
	
	@Override
	public String toString () {
		return nome + '\t' + moeda + litro;
	}
	
	@Override
	public boolean equals (Object o) {
		if(o == null)
			return false;
		String s;
		if(o.getClass() == String.class)
			s = (String) o;
		else if(o.getClass() == this.getClass())
			s = ((Combustivel) o).nome;
		else s = o.toString();
		return s.trim().toUpperCase().equals(this.nome.toUpperCase().trim());
	}
	
	public Combustivel () {
		this("");
	}
	public Combustivel (String nome) {
		this(nome,0.01f);	
	}
	public Combustivel (String nome, float custo) {
		setNome(nome);
		setCusto(custo);
	}

	
	public static String getMoeda() { return moeda; }
	public static void setMoeda(String moeda) {
		Combustivel.moeda = moeda;
	}

	
	public String getNome() {	return nome;	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getCusto() {	return litro;	}
	public void setCusto(float valor) {
		this.litro = valor;
	}
	
	public float litros (float valor) {
		return valor/this.litro;
	}
	public float valor (float litros) {
		return this.litro*litros;
	}

}
