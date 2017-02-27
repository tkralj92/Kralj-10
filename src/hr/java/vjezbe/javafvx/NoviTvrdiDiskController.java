package hr.java.vjezbe.javafvx;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import hr.java.vjezbe.entitet.MaticnaPloca;
import hr.java.vjezbe.entitet.TvrdiDisk;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class NoviTvrdiDiskController implements Initializable{
	
	@FXML
	TextField nazivProizvodaca;
	@FXML
	TextField tip;
	@FXML
	TextField kapacitet;
	@FXML
	TextField cijena;
	
	
	public void spremiMaticnuPlocu(){
		if(nazivProizvodaca.getText().isEmpty()){
			greska();
			return;
		}
		String naziv = nazivProizvodaca.getText();
		
		if(tip.getText().isEmpty()){
			greska();
			return;
		}
		String t = tip.getText();
		
		if(kapacitet.getText().isEmpty() ){
			greska();
			return;
		}
		double kap = new Double(kapacitet.getText());
		
		if(cijena.getText().isEmpty()){
			greska();
			return;
		}
		BigDecimal cij = new BigDecimal(cijena.getText().toString());
		
		TvrdiDisk mat = new TvrdiDisk(naziv, t, kap, cij);
		
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
		
	}
	
}
