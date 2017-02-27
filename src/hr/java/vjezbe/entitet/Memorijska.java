package hr.java.vjezbe.entitet;

public interface Memorijska {

	void uvecajKapacitet();
	
	public static double pretvoriUTB(double kapacitet){
		return kapacitet/1024;
		
	}
	
}
