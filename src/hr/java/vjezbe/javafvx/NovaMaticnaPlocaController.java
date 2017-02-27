package hr.java.vjezbe.javafvx;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import hr.java.vjezbe.javafvx.PocetniEkranController;
import hr.java.vjezbe.entitet.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NovaMaticnaPlocaController implements Initializable{
	
	@FXML
	TextField nazivProizvodaca;
	@FXML
	TextField tipMaticnePloce;
	@FXML
	ComboBox<String> tipSuceljaProcesora;
	@FXML
	ComboBox<Integer> brojMemorijskihModula;
	@FXML
	TextField cijena;
	
	
	public void spremiMaticnuPlocu(){
		if(nazivProizvodaca.getText().isEmpty()){
			greska();
			return;
		}
		String naziv = nazivProizvodaca.getText();
		
		if(tipMaticnePloce.getText().isEmpty()){
			greska();
			return;
		}
		String tip = tipMaticnePloce.getText();
		if(tipSuceljaProcesora.getValue()==null){
			greska();
			return;
		}
		String tipSP = tipSuceljaProcesora.getValue();
		if(brojMemorijskihModula.getValue() == null  ){
			greska();
			return;
		}
		Integer brMem = brojMemorijskihModula.getValue();
		
		if(cijena.getText().isEmpty()){
			greska();
			return;
		}
		BigDecimal cij = new BigDecimal(cijena.getText().toString());
		
		MaticnaPloca mat = new MaticnaPloca(naziv, tip, tipSP, brMem, cij);
		
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
		
		tipSuceljaProcesora.getItems().addAll(Arrays.asList("SOCKET_LGA_1151","SOCKET_G3","SOCKET_G4")); 
		brojMemorijskihModula.getItems().addAll(Arrays.asList(1,2,3,4));
	}
	
	

}
