package hr.java.vjezbe.datoteke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import hr.java.vjezbe.entitet.Komponenta;
import hr.java.vjezbe.entitet.MaticnaPloca;
import hr.java.vjezbe.entitet.Procesor;
import hr.java.vjezbe.entitet.RadnaMemorija;
import hr.java.vjezbe.entitet.TvrdiDisk;

public class Datoteke {
	
	public int odredivanjeKlase(Komponenta kom){
		if(kom.tip().equals("Maticna")){
			//spremiMaticnuPlocu((MaticnaPloca) kom);
			return 0;
		}
		if(kom.tip().equals("Procesor")){
			//spremiProcesor((Procesor) kom);
			return 1;
		}
		if(kom.tip().equals("Radna")){
			//spremiRadnuMemoriju((RadnaMemorija) kom);
			return 2;
		}
		//spremiTvrdiDisk((TvrdiDisk) kom);
		return 3;
	}
	
	
	public void spremiMaticnuPlocu(MaticnaPloca k){
		FileWriter outputStream;
		try {
			outputStream = new FileWriter("dat/MaticnaPloca.txt", true);
			outputStream.append("\n" + k.getTip());
			outputStream.append("\n" + k.getTipSuceljaProcesora()  );
			outputStream.append("\n" + k.getBrojMemorijskihModula());
			outputStream.append("\n" + k.getCijena());
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e){			
		}
	}	
	public void spremiProcesor(Procesor k){
		FileWriter outputStream;
		try {
			outputStream = new FileWriter("dat/procesori.txt", true);
			outputStream.append("\n" + k.getTip());
			outputStream.append("\n" + k.getTipSucelja());
			outputStream.append("\n" + k.getBrzina());
			outputStream.append("\n" + k.getCijena());
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e){			
		}
	}
	public void spremiRadnuMemoriju(RadnaMemorija k){
		FileWriter outputStream;
		try {
			outputStream = new FileWriter("dat/radneMemorije.txt", true);
			outputStream.append("\n" + k.getTip());
			outputStream.append("\n" + k.getBrojModulaRadneMemorije());
			outputStream.append("\n" + k.getCijena());
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e){			
		}
	}
	public void spremiTvrdiDisk(TvrdiDisk k){
		FileWriter outputStream;
		try {
			outputStream = new FileWriter("dat/tvrdiDisk.txt", true);
			outputStream.append("\n" + k.getTip());
			outputStream.append("\n" + k.getKapacitet());
			outputStream.append("\n" + k.getCijena());
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e){			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
