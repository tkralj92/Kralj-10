package hr.java.vjezbe.javafvx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
//MOJ PROJEKT
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.datoteke.Datoteke;
import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.javafvx.GlavnaDatoteke;
import hr.vjezbe.tecajnica.TecajnicaNit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PocetniEkranController  implements Initializable{
	@FXML
	public ListView<Komponenta> komponenteList;
	@FXML
	public ListView<Komponenta> racunaloLista;
	@FXML
	public Button dodajButton;
	@FXML
	public Button obrisiButton;
	@FXML
	public   TextField cijenaKune;
	@FXML
	public   TextField cijenaEuri;
	@FXML
	public DatePicker kalendar;
	
	public LocalDate datum;
	public static int novaNit = 0;	
	public int brModula = 0;
	
	
	
	public static  List<Komponenta> listaKomponenti = new ArrayList<>();
	public Komponenta odabranaKomponenta;	
	
	public static ObservableList<Komponenta> lijevaLista = FXCollections.observableArrayList();	
	public static ObservableList<Komponenta> desnaLista = FXCollections.observableArrayList();
	
	
	
	@Override
	public  void initialize(URL location, ResourceBundle resources) {					
		try{
			listaKomponenti= BazaPodataka.dohvatiMaticnePloce();
			lijevaLista.addAll(listaKomponenti);
			listaKomponenti= BazaPodataka.dohvatiProcesore();
			lijevaLista.addAll(listaKomponenti);
			listaKomponenti= BazaPodataka.dohvatiRadneMemorije();
			lijevaLista.addAll(listaKomponenti);
			listaKomponenti= BazaPodataka.dohvatiTvrdeDiskove();
			lijevaLista.addAll(listaKomponenti);
		} catch (Exception e) {
			e.printStackTrace();	
		}	
		
		
		
		komponenteList.setItems(lijevaLista);	
		racunaloLista.setItems(desnaLista);
		
		komponenteList.getSelectionModel().selectedItemProperty().
			addListener(new ChangeListener<Komponenta>() {		
			    @Override
			    public void changed(ObservableValue<? extends Komponenta> observable, Komponenta oldValue, Komponenta newValue) {
			        odabranaKomponenta = newValue;
			        //System.out.println(odabranaKomponenta);
			    }
			});
		racunaloLista.getSelectionModel().selectedItemProperty().
			addListener(new ChangeListener<Komponenta>() {		
			    @Override
			    public void changed(ObservableValue<? extends Komponenta> observable, Komponenta oldValue, Komponenta newValue) {
			        odabranaKomponenta = newValue;		       
			    }
			});			
	}	
	
	public static BigDecimal getTecaj(){
		return TecajnicaNit.tecaj;
	}
	
	@FXML
	public void izracunCijene(){
		BigDecimal cijena = new BigDecimal(0);
		BigDecimal tecaj = getTecaj();
		for(Komponenta kom : desnaLista){
			cijena =cijena.add(kom.getCijena()); 
		}
		cijenaKune.setText(cijena.toString());
		if(tecaj != null){
			cijenaEuri.setText(cijena.divide(tecaj, 2).toString());
		}else{
			cijenaEuri.setText("POGRESKA!");
		}
	}
	
	@FXML
	public void odabirDatuma(){	
		novaNit++;		
		//System.out.println("usao");
		datum = kalendar.getValue();
		//System.out.println(datum);
		TecajnicaNit nit1 = new TecajnicaNit("978EUR", datum);
		ExecutorService executorService= Executors.newCachedThreadPool();
		executorService.execute(nit1);
		if(TecajnicaNit.tecaj == null)  kriviDatum();
		executorService.shutdown();
		
	}
	public void kriviDatum(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Teèaj nedostupan"); 
		alert.setHeaderText("Odabrli ste datum za koji teèaj ne postoji!");
		alert.setContentText("Molim odaberite ispravan datum!");
		alert.showAndWait();
	}
	
	
	public static void dodajKomponentu(Komponenta kom){		
		
		lijevaLista.add(kom);			
		Datoteke dat = new Datoteke();
		int vrstaKomponente = dat.odredivanjeKlase(kom);
		
		if(vrstaKomponente == 0){			
		try {
			MaticnaPloca mat = (MaticnaPloca) kom;
			BazaPodataka.spremiMaticnuPlocu(mat);
			return;
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}
		if(vrstaKomponente == 1){			
			try {
					Procesor procesor = (Procesor) kom;
					BazaPodataka.spremiProcesor(procesor);
					return;
				} catch (Exception e) {					
					e.printStackTrace();
				}
		}
		if(vrstaKomponente == 2){			
			try {
					RadnaMemorija radna = (RadnaMemorija) kom;
					BazaPodataka.spremiRadnuMemoriju(radna);
					return;
				} catch (Exception e) {					
					e.printStackTrace();
				}
		}
		if(vrstaKomponente == 3){			
			try {
					TvrdiDisk tvrdi = (TvrdiDisk) kom;
					BazaPodataka.spremiTvrdiDisk(tvrdi);
					return;
				} catch (Exception e) {					
					e.printStackTrace();
				}
		}
		
		
	}
	
	
	//EKRAN ZA UNOS PODATAKA ZA MATICNU PLOCU
	public void prikaziEkranZaNovuMaticnuPlocu() throws IOException { 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NovaMaticnaPloca.fxml")); 
		Scene scene = new Scene( (Parent) loader.load());
		Stage stage = new Stage();
		stage.setTitle("Unos nove matiène ploèe"); 
		stage.setScene(scene); 
		stage.show(); 
		
	}
	
	//EKRAN ZA UNOS PODATAKA ZA PROCESOR
	public void prikaziEkranZaNoviProcesor() throws IOException { 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NoviProcesor.fxml")); 
		Scene scene = new Scene( (Parent) loader.load());
		Stage stage = new Stage();
		stage.setTitle("Unos novog procesora"); 
		stage.setScene(scene); 
		stage.show(); 
	}
	
	//EKRAN ZA UNOS PODATAKA ZA RADNU MEMORIJU
	public void prikaziEkranZaNovuRadnuMemoriju() throws IOException { 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NovaRadnaMemorija.fxml")); 
		Scene scene = new Scene((Parent) loader.load());
		Stage stage = new Stage();
		stage.setTitle("Unos nove radne memorije"); 
		stage.setScene(scene); 
		stage.show(); 
	}
	
	//EKRAN ZA UNOS PODATAKA ZA TVRDI DISK
	public void prikaziEkranZaNoviTvrdiDisk() throws IOException { 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NoviTvrdiDisk.fxml")); 
		Scene scene = new Scene((Parent) loader.load());
		Stage stage = new Stage();
		stage.setTitle("Unos novog tvrdog diska"); 
		stage.setScene(scene); 
		stage.show(); 
	}
	
	
	
	//desnaLista.add(odabranaKomponenta);
	public void dodaj(){
		  switch(odabranaKomponenta.tip()){
		  	case "Procesor":
		  		if(desnaLista.stream().anyMatch(p -> p.tip().equals("Procesor"))){
		  			uzbuna(odabranaKomponenta);
		  			break;		  			
		  		}
		  		if(desnaLista.stream().anyMatch(p -> p.tip().equals("Maticna"))){
		  			Procesor procesor = (Procesor) odabranaKomponenta;
		  			MaticnaPloca maticnaPloca  = (MaticnaPloca) desnaLista.stream().filter(p -> p.tip().equals("Maticna")).findFirst().get();
		  			if(maticnaPloca.getTipSuceljaProcesora().equals(procesor.getTipSucelja())){
		  				desnaLista.add(odabranaKomponenta);
		  				break;
		  			}else{
		  				uzbunaNekompatibilnost();
		  				break;
		  			}
		  		}
		  		desnaLista.add(odabranaKomponenta);
		  		break;
		  	case "Maticna":
		  		if(brModula > ((MaticnaPloca)odabranaKomponenta).getBrojMemorijskihModula()){
		  			uzbuna(odabranaKomponenta);
		  			break;
		  		}
		  		if(desnaLista.stream().anyMatch(p -> p.tip().equals("Maticna"))){
		  			uzbuna(odabranaKomponenta);
		  			break;
		  		}
		  		if(desnaLista.stream().anyMatch(p -> p.tip().equals("Procesor"))){
		  			MaticnaPloca maticnaPloca = (MaticnaPloca) odabranaKomponenta;
		  			Procesor procesor  = (Procesor) desnaLista.stream().filter(p -> p.tip().equals("Procesor")).findFirst().get();
		  			if(maticnaPloca.getTipSuceljaProcesora().equals(procesor.getTipSucelja())){
		  				desnaLista.add(odabranaKomponenta);
		  				break;
		  			}else{
		  				uzbunaNekompatibilnost();
		  				break;
		  			}
		  		}
		  		desnaLista.add(odabranaKomponenta);
		  		break;
		  	case "Radna":
		  		if(desnaLista.stream().anyMatch(p -> p.tip().equals("Maticna"))){
		  			MaticnaPloca maticnaPloca  = (MaticnaPloca) desnaLista.stream().filter(p -> p.tip().equals("Maticna")).findFirst().get();
		  			if(maticnaPloca.getBrojMemorijskihModula()>=(brModula+1)){
		  				brModula++;
		  				desnaLista.add(odabranaKomponenta);
		  				break;
		  			}else{
		  				uzbunaModuli();
		  				break;
		  			}
		  		}else{
		  			brModula++;
	  				desnaLista.add(odabranaKomponenta);
	  				break;
		  		}		  		
		  	default:
		  		desnaLista.add(odabranaKomponenta);
		  		break;
		  }	
		  izracunCijene();
	}
		
	public void obrisi(){
		racunaloLista.getItems().remove(odabranaKomponenta);
		if(brModula !=0){
		if(odabranaKomponenta.tip().equals("Radna"))
			brModula--;
		}
		izracunCijene();
	}
	
	public void obrisiSve(){
		racunaloLista.getItems().removeAll(desnaLista);		
	}
	public void uzbunaNedostaje(){				
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Pogreška"); 
		alert.setHeaderText("Nisu odabrane sve komponente");
		alert.setContentText("Konfiguracija racunala se mora sastojati od matiène ploèe, procesora, radne memorije i tvrdog diska!");
		alert.showAndWait();		
	}
	
	public void uzbuna(Komponenta k){
		String ime = k.getClass().getSimpleName();		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Pogreška"); 
		alert.setHeaderText(ime+" veæ postoji");
		alert.setContentText("Veæ ste odabrali "+ime+"!");
		alert.showAndWait();		
	}
	public void uzbunaNekompatibilnost(){		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Pogreška"); 
		alert.setHeaderText("Nekompatibilnost procesora i matiène ploèe");
		alert.setContentText("Matièna ploèa i procesor nisu kompatibilni po pitanju suèelja!");
		alert.showAndWait();		
	}
	public void uzbunaModuli (){		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Pogreška"); 
		alert.setHeaderText("Neispravan broj modula radne memorije!");
		alert.setContentText("Broj modula radne memorije i kapaciteta maticne ploce nije kompitabilan!");
		alert.showAndWait();		
	}
	public void uspjesnoSpremanje(){		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Uspješno spremanje!"); 
		alert.setHeaderText("Podaci uspjesno spremljeni!");
		alert.setContentText("Podaci o odabranoj konfiguraciji racunalauspjesno spremljeni u datoteku konfiguracija.txx!");
		alert.showAndWait();		
	}
	
	public void spremikonfiguraciju(){
		if(desnaLista.stream().anyMatch(p -> p.tip().equals("Maticna"))){
			if(desnaLista.stream().anyMatch(p -> p.tip().equals("Procesor"))){
				if(desnaLista.stream().anyMatch(p -> p.tip().equals("Radna"))){
					if(desnaLista.stream().anyMatch(p -> p.tip().equals("Tvrdi"))){
						
						Procesor procesor = (Procesor) desnaLista.stream().filter(p -> p.tip().equals("Procesor")).findFirst().get();
						MaticnaPloca maticnaPloca  = (MaticnaPloca) desnaLista.stream().filter(p -> p.tip().equals("Maticna")).findFirst().get();
						RadnaMemorija radna =  (RadnaMemorija) desnaLista.stream().filter(p -> p.tip().equals("Radna")).findFirst().get();
						TvrdiDisk tvrdi = (TvrdiDisk) desnaLista.stream().filter(p -> p.tip().equals("Tvrdi")).findFirst().get();
						Racunalo racunalo = new Racunalo(maticnaPloca, procesor, radna, tvrdi, maticnaPloca.getBrojMemorijskihModula());
						//try{
							System.out.println("Piše se u bazu.");
						/*Formatter izlaz = new Formatter("konfiguracija.txt");
						izlaz.format("%s %n %s %n %s %n %s %n", procesor, maticnaPloca, radna, tvrdi);
						izlaz.close();*/
						try {
							BazaPodataka.spremiRacunalo(racunalo);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return;
						}
					}
				}
			}	
		uzbunaNedostaje();
	}
}