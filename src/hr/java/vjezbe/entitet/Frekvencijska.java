package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.math.MathContext;

public interface Frekvencijska {

	default public BigDecimal overclock(BigDecimal brzina){
		MathContext mc = new MathContext(2);
		BigDecimal mn = new BigDecimal("1.5");
		brzina = brzina.multiply(mn, mc);
		
		return brzina;
		
	}
	
}
