package airlinereservationsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FlightDescriptionTest {

	@Test
	public void checkTimeBadFormatCorrect() {
		boolean resp = FlightDescription.check_time("23:59");
		assertEquals(true, resp); 
	}
	
	@Test
	public void checkTimeBadFormatLimiteSuperior() {
		boolean resp = FlightDescription.check_time("23:60");
		assertEquals(false, resp); 
	}
	
	@Test
	public void checkTimeBadFormatLimiteInferior() {
		boolean resp = FlightDescription.check_time("-1:00");
		assertEquals(false, resp); 
	}
	
	@Test
	public void checkTimeBadFormatMinutoInseridoErrado() {
		boolean resp = FlightDescription.check_time("01:0");
		assertEquals(false, resp); 
	}
	
	@Test
	public void checkTimeBadFormatoInseridoErrado() {
		boolean resp = FlightDescription.check_time("1");
		assertEquals(false, resp); 
	}
	
//	@Test
//	public void checkTimeBadFormatoInseridoErradoCaracteres() {
//		boolean resp = FlightDescription.check_time("01:0010");
//		assertEquals(false, resp); 
//	}
//	
//	@Test
//	public void checkTimeBadFormatoInseridoCertoCaracteres() {
//		boolean resp = FlightDescription.check_time("01:001");
//		assertEquals(false, resp); 
//	}
//	
//	@Test
//	public void checkTimeBadFormatoInseridoErradoCaracteresMenor() {
//		boolean resp = FlightDescription.check_time("123");
//		assertEquals(false, resp); 
//	}

}
