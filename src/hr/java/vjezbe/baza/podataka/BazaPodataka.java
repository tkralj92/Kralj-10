package hr.java.vjezbe.baza.podataka;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.w3c.dom.ls.LSInput;

import hr.java.vjezbe.entitet.Komponenta;
import hr.java.vjezbe.entitet.MaticnaPloca;
import hr.java.vjezbe.entitet.Procesor;
import hr.java.vjezbe.entitet.Racunalo;
import hr.java.vjezbe.entitet.RadnaMemorija;
import hr.java.vjezbe.entitet.TvrdiDisk;

public class BazaPodataka {
	
	public static final String DATABASE_FILE = "bazaPodataka.properties";
	
	
	//STVARANJE VEZE SA BAZOM PODATAKA
	private static Connection spajanjeNaBazuPodataka() throws SQLException, IOException{
		Properties svojstva = new Properties();
		
		svojstva.load(new FileReader(DATABASE_FILE));
		
		String urlBazePodataka= svojstva.getProperty("bazaPodatakaUrl");
		String korisnickoIme= svojstva.getProperty("korisnickoIme");
		String lozinka = svojstva.getProperty("lozinka");
		Connection veza = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/Java-2014",
		"student", "student");
		
		return veza;
	}
	
	private static void zatvaranjeVezeNaBazuPodataka(Connection veza) throws SQLException{
		veza.close();
		
	}

	
	public static void spremiMaticnuPlocu(MaticnaPloca maticnaPloca) throws Exception {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false); 
			try { 
				PreparedStatement insertMaticnaKomponenta = veza.prepareStatement("INSERT INTO RACUNALA.KOMPONENTA (NAZIV_PROIZVODJACA, CIJENA) VALUES (?, ?)"); 
				insertMaticnaKomponenta.setString(1, maticnaPloca.getNazivProizvodaca()); 
				insertMaticnaKomponenta.setBigDecimal(2, maticnaPloca.getCijena()); 
				int i  = insertMaticnaKomponenta.executeUpdate(); 
				ResultSet generatedKeys = insertMaticnaKomponenta.getGeneratedKeys(); 
				if (generatedKeys.next()) { 
					maticnaPloca.setId(generatedKeys.getInt(1)); 
				} 
				PreparedStatement insertMaticnaPloca = veza .prepareStatement("INSERT INTO RACUNALA.MATICNA_PLOCA (ID, TIP, TIP_SUCELJA_ZA_PROCESOR, MAX_MEMORIJSKIH_MODULA) VALUES (?, ?, ?, ?)"); 
				insertMaticnaPloca.setInt(1, maticnaPloca.getId());
				insertMaticnaPloca.setString(2, maticnaPloca.getTip());
				insertMaticnaPloca.setString(3, maticnaPloca.getTipSuceljaProcesora());
				insertMaticnaPloca.setInt(4, maticnaPloca.getBrojMemorijskihModula());
				insertMaticnaPloca.executeUpdate(); 
				veza.commit(); 				
		} catch(Throwable ex) { 
				veza.rollback(); 
				throw ex; 
		} 
		zatvaranjeVezeNaBazuPodataka(veza); 
		}
	
	public static void spremiProcesor(Procesor procesor) throws Exception {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false); 
			try { 
				PreparedStatement insertProcesorKomponenta = veza.prepareStatement("INSERT INTO RACUNALA.KOMPONENTA (NAZIV_PROIZVODJACA, CIJENA) VALUES (?, ?)"); 
				insertProcesorKomponenta.setString(1, procesor.getNazivProizvodaca()); 
				insertProcesorKomponenta.setBigDecimal(2, procesor.getCijena()); 
				insertProcesorKomponenta.executeUpdate(); 
				ResultSet generatedKeys = insertProcesorKomponenta.getGeneratedKeys(); 
				if (generatedKeys.next()) { 
					procesor.setId(generatedKeys.getInt(1)); 
				} 
				PreparedStatement insertProcesor = veza.prepareStatement("INSERT INTO RACUNALA.PROCESOR (ID, TIP, TIP_SUCELJA, BRZINA) VALUES (?, ?, ?, ?)"); 
				insertProcesor.setInt(1, procesor.getId());
				insertProcesor.setString(2, procesor.getTip());
				insertProcesor.setString(3, procesor.getTipSucelja());
				insertProcesor.setBigDecimal(4, procesor.getBrzina());
				insertProcesor.executeUpdate(); 
				veza.commit(); 				
		} catch(Throwable ex) { 
				veza.rollback(); 
				throw ex; 
		} 
		zatvaranjeVezeNaBazuPodataka(veza); 
		}
	
	public static void spremiRadnuMemoriju(RadnaMemorija radnaMemorija) throws Exception {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false); 
			try { 
				PreparedStatement insertRadnaMemorija = veza.prepareStatement("INSERT INTO RACUNALA.KOMPONENTA (NAZIV_PROIZVODJACA, CIJENA) VALUES (?, ?)"); 
				insertRadnaMemorija.setString(1, radnaMemorija.getNazivProizvodaca()); 
				insertRadnaMemorija.setBigDecimal(2, radnaMemorija.getCijena()); 
				insertRadnaMemorija.executeUpdate(); 
				ResultSet generatedKeys = insertRadnaMemorija.getGeneratedKeys(); 
				if (generatedKeys.next()) { 
					radnaMemorija.setId(generatedKeys.getInt(1)); 
				} 
				PreparedStatement insertMaticnaPloca = veza .prepareStatement("INSERT INTO RACUNALA.RADNA_MEMORIJA (ID, TIP, KAPACITET) VALUES (?, ?, ?)"); 
				insertMaticnaPloca.setInt(1, radnaMemorija.getId());
				insertMaticnaPloca.setString(2, radnaMemorija.getTip());
				insertMaticnaPloca.setDouble(3, radnaMemorija.getKapacitet());			
				insertMaticnaPloca.executeUpdate(); 
				veza.commit(); 				
		} catch(Throwable ex) { 
				veza.rollback(); 
				throw ex; 
		} 
		zatvaranjeVezeNaBazuPodataka(veza); 
		}
	
	public static void spremiTvrdiDisk(TvrdiDisk tvrdiDisk) throws Exception {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false); 
			try { 
				PreparedStatement insertTvrdiDisk = veza.prepareStatement("INSERT INTO RACUNALA.KOMPONENTA (NAZIV_PROIZVODJACA, CIJENA) VALUES (?, ?)"); 
				insertTvrdiDisk.setString(1, tvrdiDisk.getNazivProizvodaca()); 
				insertTvrdiDisk.setBigDecimal(2, tvrdiDisk.getCijena()); 
				insertTvrdiDisk.executeUpdate(); 
				ResultSet generatedKeys = insertTvrdiDisk.getGeneratedKeys(); 
				if (generatedKeys.next()) { 
					tvrdiDisk.setId(generatedKeys.getInt(1)); 
				} 
				PreparedStatement insertMaticnaPloca = veza .prepareStatement("INSERT INTO RACUNALA.TVRDI_DISK (ID, TIP, KAPACITET) VALUES (?, ?, ?)"); 
				insertMaticnaPloca.setInt(1, tvrdiDisk.getId());
				insertMaticnaPloca.setString(2, tvrdiDisk.getTip());
				insertMaticnaPloca.setDouble(3, tvrdiDisk.getKapacitet());			
				insertMaticnaPloca.executeUpdate(); 
				veza.commit(); 				
		} catch(Throwable ex) { 
				veza.rollback(); 
				throw ex; 
		} 
		zatvaranjeVezeNaBazuPodataka(veza); 
		}
	
	public static void spremiRacunalo(Racunalo racunalo) throws Exception{
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false); 
			try { 
				PreparedStatement insertRacunalo = veza.prepareStatement("INSERT INTO RACUNALA.RACUNALO (MATICNA_PLOCA_ID, PROCESOR_ID, RADNA_MEMORIJA_ID, BROJ_MODULA_RADNE_MEMORIJE, TVRDI_DISK_ID) VALUES (?, ?, ?, ?, ?)"); 
				insertRacunalo.setInt(1, racunalo.getMaticnaPloca().getId());
				insertRacunalo.setInt(2, racunalo.getProcesor().getId());
				insertRacunalo.setInt(3, racunalo.getRadnaMemorija().getId());
				insertRacunalo.setInt(4, racunalo.getMaticnaPloca().getBrojMemorijskihModula());
				insertRacunalo.setInt(5, racunalo.getTvrdiDisk().getId());	
				insertRacunalo.executeUpdate(); 
				veza.commit(); 				
		} catch(Throwable ex) { 
				veza.rollback(); 
				throw ex; 
		} 
		zatvaranjeVezeNaBazuPodataka(veza); 
	}				
	
	public static List<Komponenta> dohvatiMaticnePloce() throws Exception { 
		Connection veza = spajanjeNaBazuPodataka();
		Statement statementKomponenta = veza.createStatement(); 
		ResultSet resultSetKomponenta = 
				statementKomponenta.executeQuery("SELECT * FROM RACUNALA.KOMPONENTA");
		PreparedStatement statementMaticnaPloca = veza.prepareStatement("SELECT * FROM RACUNALA.MATICNA_PLOCA WHERE ID = ?");
		List<Komponenta> listaMaticnihPloca = new ArrayList<>(); 
		
		while(resultSetKomponenta.next()) { 
			Integer id = resultSetKomponenta.getInt("ID"); 
			String nazivProizvodjaca = resultSetKomponenta.getString("NAZIV_PROIZVODJACA"); 
			BigDecimal cijena = resultSetKomponenta.getBigDecimal("CIJENA"); 
			statementMaticnaPloca.setInt(1, id); 
			ResultSet resultSetMaticnaPloca = statementMaticnaPloca.executeQuery(); 
			
				while(resultSetMaticnaPloca.next()) { 
					String tip = resultSetMaticnaPloca.getString("TIP"); 
					String tipSucelja = resultSetMaticnaPloca.getString("TIP_SUCELJA_ZA_PROCESOR"); 
					Integer maxModula = resultSetMaticnaPloca.getInt("MAX_MEMORIJSKIH_MODULA"); 
					MaticnaPloca maticnaPloca = new MaticnaPloca(nazivProizvodjaca, tip, tipSucelja, maxModula, cijena);
					maticnaPloca.setId(id); 
					listaMaticnihPloca.add(maticnaPloca); 
					} 
				} 
		zatvaranjeVezeNaBazuPodataka(veza); 
		return listaMaticnihPloca; }
	
	public static List<Komponenta> dohvatiProcesore() throws Exception { 
		Connection veza = spajanjeNaBazuPodataka();
		Statement statementKomponenta = veza.createStatement(); 
		ResultSet resultSetKomponenta = 
				statementKomponenta.executeQuery("SELECT * FROM RACUNALA.KOMPONENTA");
		PreparedStatement statementProcesor = veza.prepareStatement("SELECT * FROM RACUNALA.PROCESOR WHERE ID = ?");
		List<Komponenta> listaProcesora = new ArrayList<>(); 
		
		while(resultSetKomponenta.next()) { 
			Integer id = resultSetKomponenta.getInt("ID"); 
			String nazivProizvodjaca = resultSetKomponenta.getString("NAZIV_PROIZVODJACA"); 
			BigDecimal cijena = resultSetKomponenta.getBigDecimal("CIJENA"); 
			statementProcesor.setInt(1, id); 
			ResultSet resultSetProcesor = statementProcesor.executeQuery(); 
			
				while(resultSetProcesor.next()) { 
					String tip = resultSetProcesor.getString("TIP"); 
					String tipSucelja = resultSetProcesor.getString("TIP_SUCELJA"); 
					BigDecimal brzina = resultSetProcesor.getBigDecimal("BRZINA"); 
					Procesor procesor = new Procesor(nazivProizvodjaca, tip, tipSucelja, brzina, cijena);
					procesor.setId(id); 
					listaProcesora.add(procesor); 
					} 
				} 
		zatvaranjeVezeNaBazuPodataka(veza); 
		return listaProcesora; 
	}
	
	public static List<Komponenta> dohvatiRadneMemorije() throws Exception { 
		Connection veza = spajanjeNaBazuPodataka();
		Statement statementKomponenta = veza.createStatement(); 
		ResultSet resultSetKomponenta = 
				statementKomponenta.executeQuery("SELECT * FROM RACUNALA.KOMPONENTA");
		PreparedStatement statementRadnaMemorija = veza.prepareStatement("SELECT * FROM RACUNALA.RADNA_MEMORIJA WHERE ID = ?");
		List<Komponenta> listaRadneMemorije = new ArrayList<>(); 
		
		while(resultSetKomponenta.next()) { 
			Integer id = resultSetKomponenta.getInt("ID"); 
			String nazivProizvodjaca = resultSetKomponenta.getString("NAZIV_PROIZVODJACA"); 
			BigDecimal cijena = resultSetKomponenta.getBigDecimal("CIJENA"); 
			statementRadnaMemorija.setInt(1, id); 
			ResultSet resultSetRadnaMemorija = statementRadnaMemorija.executeQuery(); 
			
				while(resultSetRadnaMemorija.next()) { 
					String tip = resultSetRadnaMemorija.getString("TIP"); 
					Double kapacitet = resultSetRadnaMemorija.getDouble("KAPACITET"); 
					RadnaMemorija radnaMemorija = new RadnaMemorija(nazivProizvodjaca, tip, kapacitet, cijena);
					radnaMemorija.setId(id); 
					listaRadneMemorije.add(radnaMemorija); 
					} 
				} 
		zatvaranjeVezeNaBazuPodataka(veza); 
		return listaRadneMemorije; 
	}
	
	public static List<Komponenta> dohvatiTvrdeDiskove() throws Exception { 
		Connection veza = spajanjeNaBazuPodataka();
		Statement statementKomponenta = veza.createStatement(); 
		ResultSet resultSetKomponenta = 
				statementKomponenta.executeQuery("SELECT * FROM RACUNALA.KOMPONENTA");
		PreparedStatement statementTvrdiDisk = veza.prepareStatement("SELECT * FROM RACUNALA.TVRDI_DISK WHERE ID = ?");
		List<Komponenta> listaTvrdihdiskova = new ArrayList<>(); 
		
		while(resultSetKomponenta.next()) { 
			Integer id = resultSetKomponenta.getInt("ID"); 
			String nazivProizvodjaca = resultSetKomponenta.getString("NAZIV_PROIZVODJACA"); 
			BigDecimal cijena = resultSetKomponenta.getBigDecimal("CIJENA"); 
			statementTvrdiDisk.setInt(1, id); 
			ResultSet resultSetTvrdiDisk = statementTvrdiDisk.executeQuery(); 
			
				while(resultSetTvrdiDisk.next()) { 
					String tip = resultSetTvrdiDisk.getString("TIP"); 
					Double kapacitet = resultSetTvrdiDisk.getDouble("KAPACITET"); 
					TvrdiDisk tvrdiDisk = new TvrdiDisk(nazivProizvodjaca, tip, kapacitet, cijena);
					tvrdiDisk.setId(id); 
					listaTvrdihdiskova.add(tvrdiDisk); 
					} 
				} 
		zatvaranjeVezeNaBazuPodataka(veza); 
		return listaTvrdihdiskova; 
	}
	
	

}
