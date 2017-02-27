package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.entitet.Komponenta;

/**
 * Sadrzi konstruktore, getere i setere za klasu Radna memorija.
 * @author tkralj
 * @param uvecajKapacitet Uvecaca kapacitet radne memorije za 100%.
 */
public class RadnaMemorija extends Komponenta implements Memorijska{
	
	private String tip;
	private double kapacitet;
	private int brojModulaRadneMemorije;
	
	public RadnaMemorija(String nazivProizvodac, String tip, double kapacitet) {
		super(nazivProizvodac);
		this.tip = tip;
		this.kapacitet = kapacitet;
	}
	
	public RadnaMemorija(String tip, double kapacitet,  BigDecimal cijena) {
		super(cijena);
		this.tip = tip;
		this.kapacitet = kapacitet;
		
	}
	
	public RadnaMemorija(String nazivProizvodac, String tip, double kapacitet,  BigDecimal cijena) {
		super(nazivProizvodac, cijena);
		this.tip = tip;
		this.kapacitet = kapacitet;
		
	}
	
	public int getBrojModulaRadneMemorije() {
		return brojModulaRadneMemorije;
	}

	public void setBrojModulaRadneMemorije(int brojModulaRadneMemorije) {
		this.brojModulaRadneMemorije = brojModulaRadneMemorije;
	}

	public RadnaMemorija(String nazivProizvodaca) {
		super(nazivProizvodaca);
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
		
		  return "Radna memorija: tip=" +tip+ ", "
		  		+ "kapacitet="+ kapacitet+ " cijena="+getCijena();				  
	}

	@Override
	public String tip() {
		// TODO Auto-generated method stub
		return "Radna";
	}  
	
}
