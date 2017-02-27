package hr.java.vjezbe.javafvx;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import hr.java.vjezbe.entitet.*;


public class GlavnaDatoteke {	

	public List<Komponenta> ucitajSveDatoteke(){	
		List<Komponenta> sveDatoteke  = new ArrayList<Komponenta>();
		List<Komponenta> sveDatoteke1  = new ArrayList<Komponenta>();
		GlavnaDatoteke glavna = new GlavnaDatoteke();
		
		sveDatoteke.addAll(glavna.ucitajProcesore());
		sveDatoteke.addAll( glavna.ucitajMaticnePloce());
		sveDatoteke.addAll(glavna.ucitajRadneMemorije());
		sveDatoteke.addAll(glavna.ucitajTvrdeDiskove());
		
		return sveDatoteke;		
	}
	
	public List<Procesor> ucitajProcesore(){
		Scanner in = null;
		List<Procesor> listaProcesora = new ArrayList<Procesor>();
		
		try{
			in = new Scanner(new File("dat/procesori.txt"));
			System.out.println("Datoteka je otvorena");
		}
		catch(FileNotFoundException e){
			System.out.println("Datoteka nije otvorena");
		}
	
		while(in.hasNext()){
			String tip = in.nextLine();
			String tipSucelja = in.nextLine();
			String brzina = in.nextLine();
			String cijena = in.nextLine();
			listaProcesora.add(new Procesor(tip, tipSucelja, new BigDecimal(brzina), new BigDecimal(cijena)));
		}	
		in.close();	
		return listaProcesora;
	}
	
	public List<RadnaMemorija> ucitajRadneMemorije(){
		Scanner in = null;
		List<RadnaMemorija> listaRadnihMemorija = new ArrayList<RadnaMemorija>();
		
		try{
			in = new Scanner(new File("dat/radneMemorije.txt"));
			System.out.println("Datoteka je otvorena");
		}
		catch(FileNotFoundException e){
			System.out.println("Datoteka nije otvorena");
		}
	
		while(in.hasNext()){
			String tip = in.nextLine();
			String kapacitet = in.nextLine();
			String cijena = in.nextLine();
			listaRadnihMemorija.add(new RadnaMemorija(tip,Double.parseDouble(kapacitet), new BigDecimal(cijena)));
		}	
		in.close();	
		return listaRadnihMemorija;
	}
	
	public List<MaticnaPloca> ucitajMaticnePloce(){
		Scanner in = null;
		List<MaticnaPloca> listaMaticnihPloca = new ArrayList<MaticnaPloca>();
		
		try{
			in = new Scanner(new File("dat/MaticnaPloca.txt"));
			System.out.println("Datoteka je otvorena");
		}
		catch(FileNotFoundException e){
			System.out.println("Datoteka nije otvorena");
		}
	
		while(in.hasNext()){
			String tip = in.nextLine();
			String tipSuceljaProcesora = in.nextLine();
			String brojMemorijskihModula = in.nextLine();
			String cijena = in.nextLine();
			listaMaticnihPloca.add(new MaticnaPloca(tip, tipSuceljaProcesora, new Integer(brojMemorijskihModula), new BigDecimal(cijena)));
		}	
		in.close();	
		return listaMaticnihPloca;
	}
	
	public List<TvrdiDisk> ucitajTvrdeDiskove(){
		Scanner in = null;
		List<TvrdiDisk> listaTvrdihDiskova = new ArrayList<TvrdiDisk>();
		
		try{
			in = new Scanner(new File("dat/tvrdiDisk.txt"));
			System.out.println("Datoteka je otvorena");
		}
		catch(FileNotFoundException e){
			System.out.println("Datoteka nije otvorena");
		}
	
		while(in.hasNext()){
			String tip = in.nextLine();
			String kapacitet = in.nextLine();
			String cijena = in.nextLine();
			listaTvrdihDiskova.add(new TvrdiDisk(tip, Double.parseDouble(kapacitet), new BigDecimal(cijena)));
		}	
		in.close();	
		return listaTvrdihDiskova;
	}
	
}

		