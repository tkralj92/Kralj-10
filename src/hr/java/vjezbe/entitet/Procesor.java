package hr.java.vjezbe.entitet;
/**
 * Sadrzi podatke klase Procesor.
 * @param suceljaProcesora je polje sa mogucim izborima za sucelje procesora.
 * 
 * Sadrzi kontruktor koji prima svih cetri varijable i getere i setere za sve vrijednosti.
 *  
 */

import java.math.BigDecimal;

import hr.java.vjezbe.entitet.Komponenta;

public class Procesor extends Komponenta implements Frekvencijska{

	private String tip;
	private String tipSucelja;
	private BigDecimal brzina;
	//private Integer brojJezgara;
	//public static final String[] suceljaProcesora = {"socket AM2", " socket AM3", " LGA 1151", "socket G3"};
	
	public Procesor(String nazivProizvodaca, String tip, String tipSucelja, BigDecimal brzina/*, Integer brojJezgara*/){
		super(nazivProizvodaca);
		this.tip= tip;
		this.tipSucelja= tipSucelja;
		this.brzina = brzina;		
	//	this.brojJezgara = brojJezgara;
	}
	
	public Procesor(String tip, String tipSucelja, BigDecimal brzina, BigDecimal cijena){
		super(cijena);
		this.tip= tip;
		this.tipSucelja= tipSucelja;
		this.brzina = brzina;		
	}
	
	public Procesor(String nazivProizvodaca, String tip, String tipSucelja, BigDecimal brzina, BigDecimal cijena/*, Integer brojJezgara*/){
		super(nazivProizvodaca, cijena);
		this.tip= tip;
		this.tipSucelja= tipSucelja;
		this.brzina = brzina;		
	//	this.brojJezgara = brojJezgara;
	}
	
	
	public Procesor(String nazivProizvodaca) {
		super(nazivProizvodaca);
	}
	
	public Procesor( ) {
		super();
	}
	@Override
	public String tip(){
		return "Procesor";
	}


	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getTipSucelja() {
		return tipSucelja;
	}

	public void setTipSucelja(String tipSucelja) {
		this.tipSucelja = tipSucelja;
	}

	public BigDecimal getBrzina() {
		return brzina;
	}

	public void setBrzina(BigDecimal brzina) {
		this.brzina = brzina;
	}

/*public Integer getBrojJezgara() {
		return brojJezgara;
	}

	public void setBrojJezgara(Integer brojJezgara) {
		this.brojJezgara = brojJezgara;
	}*/
	
	@Override
	public String toString(){
		  return "Procesor: tip="+ tip + ", "
		  		+ "Tip suèelja="+ tipSucelja +", "
		  		+ "brzina="+brzina+" cijena=" +getCijena();				  
		 }  
	
	
}
