package hr.java.vjezbe.entitet;

public enum SuceljeProcesora {
	
	PRVI ("SOCKET_AM2", "1"), 
	DRUGI ("SOCKET_AM3", "2"), 
	TRECI ("SOCKET_LGA_1151", "3"), 
	CETVRTI ("SOCKET_G3", "4"); 
	
	
	private final String kod;
	private final String broj;
	
	SuceljeProcesora(String sucelje, String broj) {
		kod = sucelje;
		this.broj = broj;
	}

	public String getKod() {
		return kod;
	}
	
	public String getBroj(){
		return broj;
	}
	
	
	
}
