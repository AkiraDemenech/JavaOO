package br.uel.cce.dc.cc.poo.posto;

import java.util.ArrayList;
import java.util.List;

public class Bomba {
	
	private static int id_count = 0;
	private int id;
	private List<Combustivel> combustiveis;
	
	public Bomba () {
		this(id_count);
	}
	
	public Bomba (int id) {
		this(id,new ArrayList<Combustivel>());
	}
	
	public Bomba (int id, List<Combustivel> combustiveis) {
		this.combustiveis = combustiveis;
		this.id = id;		
		if(id >= id_count)
			id_count = id + 1;
	}
	
	@Override
	public boolean equals (Object obj) {
		if(obj == null)
			return false;
		return obj.getClass() == this.getClass() && this.equals(((Bomba)obj).id);
	}
	public boolean equals (int id) {
		return this.id == id;
	}
	
	public int getId () {
		return this.id;
	}
	public List<Combustivel> getCombustiveis () {
		return this.combustiveis;
	}
	
	@Override	
	public String toString () {
		StringBuffer t = new StringBuffer();
		t.append("Bomba\t").append(id).append('\n');
		for(Combustivel c: combustiveis) 
			t.append('\t').append(c.toString()).append('\n');
		return t.toString();
	}
	

}
