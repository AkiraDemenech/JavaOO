package br.uel.cce.dc.cc.poo.banco.conta;

import java.math.BigDecimal;

public class ContaPoupanca extends ContaBancaria {
	
	public ContaPoupanca () {
		super();
	}
	public ContaPoupanca (ContaBancaria conta) {
		super(conta);
		if(conta.getClass() == this.getClass()) 
			this.setDiaRendimento(((ContaPoupanca)conta).getDiaRendimento());
	}
	public ContaPoupanca (String nomeCliente) {
		super(nomeCliente);
	}
	public ContaPoupanca (String nomeCliente, String numeroConta) {
		super(nomeCliente, numeroConta);
	}
	
	public void calcularNovoSaldo (BigDecimal taxaRendimento) {
		if(getSaldo() != null && taxaRendimento != null)
			depositar(getSaldo().multiply(taxaRendimento).divide(new BigDecimal(100)));
	}
	public void calcularNovoSaldo (double taxaRendimento) {
		calcularNovoSaldo(new BigDecimal(taxaRendimento));
	}
	
	
	private int diaRendimento = 1;
	
	public int getDiaRendimento () {
		return this.diaRendimento;
	}
	public void setDiaRendimento (int dia) {
		this.diaRendimento = dia;
	}
	
	@Override
	public String toString () {
		return super.toString() + "\n\tRendimento (Conta Poupança): dia " + getDiaRendimento(); 
	}
	

}
