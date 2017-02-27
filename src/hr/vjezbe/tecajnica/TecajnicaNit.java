package hr.vjezbe.tecajnica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import hr.java.vjezbe.javafvx.PocetniEkranController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TecajnicaNit implements Runnable {
	
	private int sleepTime = 1000;
	private String valuta;
	private LocalDate datum;
	public static BigDecimal tecaj = null;
	
	@Override
	public void run(){
		int brDretve = PocetniEkranController.novaNit;
		//System.out.println("usao u nit");
		while(brDretve == PocetniEkranController.novaNit){
			
			try {
				tecaj = Tecajnica.dohvatiTecaj(valuta, datum);
			} catch (IOException e1) {
				tecaj = null;
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			if(tecaj != null)
			System.out.println("Preuzet je tacaj za datum"+datum+"za 1 EUR = " +tecaj+"KN.");			
			
			
		}
	}

	public TecajnicaNit(String valuta, LocalDate datum) {
		super();
		this.valuta = valuta;
		this.datum = datum;
	}
	
	
	
	
	
	
}
