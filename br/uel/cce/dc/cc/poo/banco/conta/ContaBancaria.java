package br.uel.cce.dc.cc.poo.banco.conta;

import java.math.BigDecimal;

public class ContaBancaria {
	
	public ContaBancaria () {
		
	}	
	public ContaBancaria (ContaBancaria conta) {
		if(conta != null) {
			this.setCliente(conta.getCliente());
			this.setNumero(conta.getNumero());
			this.setSaldo(conta.getSaldo());
		}
	}
	public ContaBancaria (String nomeCliente) {
		setCliente(nomeCliente);
	}
	public ContaBancaria (String nomeCliente, String numeroConta) {
		this(nomeCliente);
		setNumero(numeroConta);				
	}
	
	
	public boolean sacar (double saque) {
		return sacar(new BigDecimal(saque));		
	}
	public boolean sacar (BigDecimal saque) {					
		if(saldo != null && saque != null && saldo.compareTo(saque) >= 0) {
			setSaldo(saldo.subtract(saque));
			return true;
		}
		return false;			
	}
	
	public void depositar (double deposito) {
		depositar(new BigDecimal(deposito));
	}
	public void depositar (BigDecimal deposito) {
		if(saldo == null)
			setSaldo(deposito);
		else if(deposito != null)
			setSaldo(saldo.add(deposito));
	}
	
	
	
	private String cliente = null;
	private String numeroConta = null;
	private BigDecimal saldo = null;
	
	public void setNumero (String numero) {
	//	System.out.println(numero);
		this.numeroConta = numero;
	}
	public void setCliente (String nome) {
		if(nome != null)
			nome = nome.toUpperCase().trim();
		this.cliente = nome;
	}
	
	public String getCliente () {
		return this.cliente;
	}
	public String getNumero () {
		return this.numeroConta;
	}
	public BigDecimal getSaldo () {
		return this.saldo;
	}
	
	protected void setSaldo (BigDecimal saldo) {
		// nem todo mundo deveria ter o direito de alterar o saldo de alguém
		if(saldo == null)
			saldo = new BigDecimal(0);
		this.saldo = saldo;
	}
	
	@Override
	public String toString () {
		return "Conta Bancária " + getNumero() + " (" + getCliente() + "):\n\tSaldo: R$" + getSaldo(); 
	}
	
	@Override
	public boolean equals (Object obj) {				
		if(obj == this)
			return true;
		if(obj == null)
			return false;		
		return getNumero() != null && getNumero().equals(((ContaBancaria)obj).getNumero());
	}
	
	

}
