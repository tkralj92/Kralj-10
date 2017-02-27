package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Sadrzi sve podklase Komponente. 
 * @author tkralj
 *
 */
public class Racunalo {

	private MaticnaPloca maticnaPloca;
	private Procesor procesor;
	private RadnaMemorija radnaMemorija;
	private TvrdiDisk tvrdiDisk;
	private Integer brojMemorijskihModula;
	private Integer id;

	public Racunalo(MaticnaPloca maticnaPloca, Procesor procesor, RadnaMemorija radnaMemorija, TvrdiDisk tvrdiDisk, Integer brojMemorijskihModula) {
		super();
		this.maticnaPloca = maticnaPloca;
		this.procesor = procesor;
		this.radnaMemorija = radnaMemorija;
		this.tvrdiDisk = tvrdiDisk;
		this.brojMemorijskihModula = brojMemorijskihModula;
	}	

	public Racunalo() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MaticnaPloca getMaticnaPloca() {
		return maticnaPloca;
	}

	public void setMaticnaPloca(MaticnaPloca maticnaPloca) {
		this.maticnaPloca = maticnaPloca;
	}

	public Procesor getProcesor() {
		return procesor;
	}

	public void setProcesor(Procesor procesor) {
		this.procesor = procesor;
	}

	public RadnaMemorija getRadnaMemorija() {
		return radnaMemorija;
	}

	public void setRadnaMemorija(RadnaMemorija radnaMemorija) {
		this.radnaMemorija = radnaMemorija;
	}

	public TvrdiDisk getTvrdiDisk() {
		return tvrdiDisk;
	}

	public void setTvrdiDisk(TvrdiDisk tvrdiDisk) {
		this.tvrdiDisk = tvrdiDisk;
	}
	
	public Integer getBrojMemorijskihModula() {
		return brojMemorijskihModula;
	}

	public void setBrojMemorijskihModula(Integer brojMemorijskihModula) {
		this.brojMemorijskihModula = brojMemorijskihModula;
	}

	@Override
	public String toString(){
		return maticnaPloca.toString() + procesor.toString() + radnaMemorija.toString() + tvrdiDisk.toString();		
	}
	
	public BigDecimal izracunajCijenuRacunala(){
		BigDecimal cijena;
		MathContext mc = new MathContext(4);
		cijena = maticnaPloca.getCijena().add(tvrdiDisk.getCijena()).add(procesor.getCijena());
		cijena = cijena.add(radnaMemorija.getCijena().multiply(new BigDecimal(brojMemorijskihModula), mc));
		return cijena;
	}
	
}
