package br.uel.cce.dc.cc.poo.banco.conta;

import java.math.BigDecimal;

public class ContaCorrente extends ContaBancaria {
	
	public ContaCorrente () {
		super();
	}
	public ContaCorrente (ContaBancaria conta) {
		super(conta);
		if(conta.getClass() == this.getClass()) 
			this.setLimite(((ContaCorrente)conta).getLimite());					
	}
	public ContaCorrente (String nomeCliente) {
		super(nomeCliente);
	}
	public ContaCorrente (String nomeCliente, String numeroConta) {
		super(nomeCliente, numeroConta);
	}
	
	@Override
	public boolean sacar (BigDecimal saque) {
		setSaldo(getSaldo());//if(getSaldo() == null)	setSaldo(null);
		setLimite(getLimite());
		if(saque == null || saque.compareTo(limite.add(getSaldo())) > 0) 
			return false;		
		setSaldo(getSaldo().subtract(saque));
		return true;
		
		
	}
	@Override
	public boolean sacar (double saque) {
		return this.sacar(new BigDecimal(saque));
	}
	
	
	private BigDecimal limite = null;
	
	public BigDecimal getLimite () {
		return this.limite;
	}
	
	public void setLimite (BigDecimal limite) {
		if(limite == null)
			limite = new BigDecimal(0);
		this.limite = limite;
	}
	
	
	
	@Override
	public String toString () {
		return super.toString() + "\n\tLimite (Conta Corrente): R$" + getLimite(); 
	}

	
	
}
