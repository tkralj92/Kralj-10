package hr.java.vjezbe.javafvx;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import hr.java.vjezbe.entitet.MaticnaPloca;
import hr.java.vjezbe.entitet.Procesor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class NovaProcesorController implements Initializable{
	

	@FXML
	TextField nazivProizvodaca;
	@FXML
	TextField tipProcesora;
	@FXML
	ComboBox<String> tipSucelja;
	@FXML
	TextField brzina;
	@FXML
	TextField cijena;
	
	
	public void spremiProcesor(){
		if(nazivProizvodaca.getText().isEmpty()){
			greska();
			return;
		}
		String naziv = nazivProizvodaca.getText();
		
		if(tipProcesora.getText().isEmpty()){
			greska();
			return;
		}
		String tip = tipProcesora.getText();
		
		if(brzina.getText().isEmpty()){
			greska();
			return;
		}
		BigDecimal brz = new BigDecimal(brzina.getText().toString());
		
		if(tipSucelja.getValue()==null){
			greska();
			return;
		}
		String tipS = tipSucelja.getValue();
		
		if(cijena.getText().isEmpty()){
			greska();
			return;
		}
		BigDecimal cij = new BigDecimal(cijena.getText().toString());
		
		Procesor mat = new Procesor(naziv, tip, tipS, brz, cij); 
				
		System.out.println(mat);
		
		PocetniEkranController.dodajKomponentu(mat);
	}	
	
	public void greska(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Pogreška kod unosa"); 
		alert.setHeaderText("Nisu unesene sve komponente");
		alert.setContentText("Obavezan podatak");
		alert.showAndWait();	
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tipSucelja.getItems().addAll(Arrays.asList("SOCKET_LGA_1151","SOCKET_G3","SOCKET_G4")); 
		
	}
	
	

}
