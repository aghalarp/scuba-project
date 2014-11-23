import static org.junit.Assert.*;
import org.junit.Test;

/**
 * DiveTableTest
 * @author Chris Caoagdan
 *
 */

public class DiveTableTest {

	@Test
	public void checkDiveTableOutput() {
		DiveTable diveTable = new DiveTable();
		assertNotNull(diveTable.endOfDiveTable);
		assertNotNull(diveTable.repetitiveDiveTimeTable);
		assertNotNull(diveTable.surfaceIntervalTimeTable);
		//fail("Not yet implemented");
	}

	/*@Test
	public void testDiveTableFormat () {
		DiveTable diveTable = new DiveTable();
		//fail("Not yet implemented");
	}*/
	
}
