package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.entitet.Komponenta;

/**
 * Prima 4 varijable u svoj konstruktor, jednu salje nadklasi a ostale pohranjuje u unutarnje argumente
 * @author tkralj
 *
 */

public class MaticnaPloca extends Komponenta{

	private String tip;
	private String tipSuceljaProcesora;
	private Integer brojMemorijskihModula;
	
	public MaticnaPloca(String nazivProizvodaca, String tip, String tipSuceljaProcesora,
			Integer brojMemorijskihModula) {
		super(nazivProizvodaca);
		this.tip = tip;
		this.tipSuceljaProcesora = tipSuceljaProcesora;
		this.brojMemorijskihModula = brojMemorijskihModula;
	}
	
	public MaticnaPloca(String tip, String tipSuceljaProcesora,
			Integer brojMemorijskihModula, BigDecimal cijena) {
		super(cijena);
		this.tip = tip;
		this.tipSuceljaProcesora = tipSuceljaProcesora;
		this.brojMemorijskihModula = brojMemorijskihModula;
	}
	
	public MaticnaPloca(String nazivProizvodaca, String tip, String tipSuceljaProcesora,
			Integer brojMemorijskihModula, BigDecimal cijena){
		super(nazivProizvodaca, cijena);
		this.tip = tip;
		this.tipSuceljaProcesora = tipSuceljaProcesora;
		this.brojMemorijskihModula = brojMemorijskihModula;
		
	}
	
	

	public MaticnaPloca() {
		super();
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getTipSuceljaProcesora(){
		return tipSuceljaProcesora;		
	}
	
	public void setTipSuceljaProcesora(String tipSuceljaProcesora){
		this.tipSuceljaProcesora = tipSuceljaProcesora;
	}

	public Integer getBrojMemorijskihModula() {
		return brojMemorijskihModula;
	}

	public void setBrojMemorijskihModula(Integer brojMemorijskihModula) {
		this.brojMemorijskihModula = brojMemorijskihModula;
	}
	@Override
	public String tip(){
		return "Maticna";
	}
	
	@Override
	public String toString(){
		  return  "Matièna ploæa: tip="+tip +", tip suèelja procesra= "+ tipSuceljaProcesora +", "
		  		+ "Maksimalni broj memorijskih modula: " + brojMemorijskihModula ;				  
		 }  
	
}
