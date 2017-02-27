package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.entitet.Komponenta;

/**
 * Sadrzi parametre tvrdog diska
 * @author tkralj
 * @param nazivProzizvodaca 
 * @param tip
 * @param kapacitet
 * @param uvecajKapacitet Uvecava kapacitet tvrdog diska za 100%
 * 
 */
public class TvrdiDisk extends Komponenta implements Memorijska{
		
	private String tip;
	private double kapacitet;
	
	public TvrdiDisk(String nazivProizvodaca, String tip, double kapacitet) {
		super(nazivProizvodaca);		
		this.tip = tip;
		this.kapacitet = kapacitet;
	}
	
	public TvrdiDisk(String tip, double kapacitet, BigDecimal cijena) {
		super(cijena);		
		this.tip = tip;
		this.kapacitet = kapacitet;
	}
	
	public TvrdiDisk(String nazivProizvodaca, String tip, double kapacitet, BigDecimal cijena) {
		super(nazivProizvodaca, cijena);		
		this.tip = tip;
		this.kapacitet = kapacitet;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public double getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(double kapacitet) {
		this.kapacitet = kapacitet;
	}

	@Override
	public void uvecajKapacitet() {
		this.kapacitet += kapacitet; 
	}
	
	@Override
	public String toString(){
		  return "Tvrdi disk: tip=" +tip+ ", "
		  		+ "kapacitet="+ kapacitet+" cijena= "+getCijena();				  
	} 
	@Override
	public String tip() {
		// TODO Auto-generated method stub
		return "Tvrdi";
	}  
	
}
