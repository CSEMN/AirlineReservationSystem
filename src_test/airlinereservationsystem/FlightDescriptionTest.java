package airlinereservationsystem;
package airlinereservationsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FlightDescriptionTest {
	
	@Test
	public void checkTimeBadFormatLimiteSuperior() {
		FligtDescripton flight = new FlightDescription();
		resp = flight.check_time("24:00");
		assertEquals(false, resp); 
	}
	
	@Test
	public void checkTimeBadFormatLimiteInferior() {
		FligtDescripton flight = new FlightDescription();
		resp = flight.check_time("-01:00");
		assertEquals(false, resp); 
	}
}
