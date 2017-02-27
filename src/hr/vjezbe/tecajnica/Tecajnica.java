package hr.vjezbe.tecajnica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Tecajnica {

	public static synchronized BigDecimal dohvatiTecaj(String valuta, LocalDate datum) throws MalformedURLException, IOException {
		//System.out.println("usao u tecajnicu");
		BigDecimal tecaj = null;		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");		
		String adresa = "http://www.hnb.hr/tecajn/f"+datum.format(formatter)+".dat";		
		//adresa = "http://www.hnb.hr/tecajn/f251215.dat";
		//System.out.println(adresa);
		InputStream input;		
		input = new URL(adresa).openStream();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
		while((line = reader.readLine())!= null){
			if(line.startsWith(valuta)){
				String lineShorten = line.substring(31, 39);
				lineShorten = lineShorten.replace(',', '.');
				//System.out.println(line);
				//System.out.println(lineShorten);
				tecaj = new BigDecimal(lineShorten);
			}			
		}		
		
		
		
		return tecaj;
	}	
	
	
}