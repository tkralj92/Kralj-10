package hr.java.vjezbe.javafvx;
	
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import hr.vjezbe.tecajnica.Tecajnica;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("PocetniEkran.fxml"));
			Scene scene = new Scene(root,1500,475);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Odaberite komponente koje želite dodati u konfiguraciju raèunala");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {			
		launch(args);
		
	}
}
