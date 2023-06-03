package airlinereservationsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FlightDescriptionTest {

	@Test
	public void checkTimeBadFormatLimiteSuperior() {
		boolean resp = FlightDescription.check_time("23:60");
		assertEquals(false, resp); 
	}
	
	@Test
	public void checkTimeBadFormatLimiteInferior() {
		boolean resp = FlightDescription.check_time("-01:00");
		assertEquals(false, resp); 
	}

}
