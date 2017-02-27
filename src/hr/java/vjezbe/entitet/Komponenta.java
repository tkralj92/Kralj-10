package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Apstraktna nadklasasa samo jednim atributom kojeg dobiva preko konstruktora svojih podklasa.
 * @author tkralj
 *
 */

public abstract class Komponenta {

	private String nazivProizvodaca;
	private BigDecimal cijena;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Komponenta(String nazivProizvodaca) {
		super();
		this.nazivProizvodaca = nazivProizvodaca;
	}
	
	public Komponenta(BigDecimal cijena) {
		super();
		this.cijena = cijena;
	}
	
	public Komponenta(String nazivProizvodaca, BigDecimal cijena) {
		super();
		this.nazivProizvodaca = nazivProizvodaca;
		this.cijena = cijena;
	}
	
	public Komponenta() {
		super();
	}

	public String getNazivProizvodaca() {
		return nazivProizvodaca;
	}

	public void setNazivProizvodaca(String nazivProizvodaca) {
		this.nazivProizvodaca = nazivProizvodaca;
	}
	
	public BigDecimal getCijena() {
		return cijena;
	}

	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}
	
	public abstract String tip();
		
}
