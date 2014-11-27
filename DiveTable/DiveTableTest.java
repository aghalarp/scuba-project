import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * DiveTableTest
 * @author Chris Caoagdan
 *
 */

public class DiveTableTest {

	//private final String makePlain = "\033[0;0m";
	//private final String makeBold = "\033[0;1m";
	
	@Test
	public void checkDiveTableIfNotNull() {
		System.out.println("\"Test 1: Checking that maps aren't null\"");
		DiveTable diveTable = new DiveTable();
		assertNotNull("'endOfDiveTable' is null.", diveTable.endOfDiveTable);
		if (diveTable.endOfDiveTable != null) {
			System.out.println("assertNotNull: 'endOfDiveTable' is OK.");
			//System.out.println(diveTable.endOfDiveTable);
			/*Map<String, String> revEntry = new LinkedHashMap<String, String>(diveTable.endOfDiveTable.size());
			for (Map.Entry entry : diveTable.endOfDiveTable.entrySet()) {
				revEntry.put(entry.getValue(), entry.getKey());
			}*/
		}
		assertNotNull("'repetitiveDiveTimeTable' is null.", diveTable.repetitiveDiveTimeTable);
		if (diveTable.repetitiveDiveTimeTable != null) {
			System.out.println("assertNotNull: 'repetitiveDiveTimeTable' is OK.");
			//System.out.println(diveTable.repetitiveDiveTimeTable);
		}
		assertNotNull("'surfaceIntervalTimeTable' is null.", diveTable.surfaceIntervalTimeTable);
		if (diveTable.surfaceIntervalTimeTable != null) {
			System.out.println("assertNotNull: 'surfaceIntervalTimeTable' is OK.\n");
			//System.out.println(diveTable.surfaceIntervalTimeTable);
		}
		/*assertNotNull("'diveResultsMap' is null.", diveTable.diveResultsMap);
		if (diveTable.diveResultsMap != null) {
			System.out.println("assertNotNull: 'diveResultsMap' is OK.\n");
			//System.out.println(diveTable.diveResultsMap);
		}*/
	}
	
	/*@Test
	public void testDiveTableFormat () {
		DiveTable diveTable = new DiveTable();
		//fail("Not yet implemented");
	}*/
	
	@Test
	public void checkDiveInputsAndCalculations() { 
		System.out.println("\"Test 2: Checking results of calculations for dive tables\"");
        //Testing Dive Plan 1 (Legal)
		System.out.println("Test Dive Plan 1...");
        List<Integer> testDives = new ArrayList<Integer>();

        System.out.println("Dive #1\nAdding Depth: 80");
        testDives.add(80); //Depth
        assertTrue("Depth was not added correctly",testDives.get(0) == 80);
        System.out.println("Adding Time: 41");
        testDives.add(41); //Time
        assertTrue("Time was not added correctly",testDives.get(1) == 41);
        System.out.println("Adding Surface Interval Time: 118");
        testDives.add(118); //SIT
        assertTrue("Surface Interval Time was not added correctly",testDives.get(2) == 118);
        
        System.out.println("Dive #2\nAdding Depth: 20");
        testDives.add(20); //Depth
        assertTrue("Depth was not added correctly",testDives.get(3) == 20);
        System.out.println("Adding Time: 21");
        testDives.add(21); //Time
        assertTrue("Time was not added correctly",testDives.get(4) == 21);
        System.out.println("Adding Surface Interval Time: 22");
        testDives.add(22); //SIT
        assertTrue("Surface Interval Time was not added correctly",testDives.get(5) == 22);

        System.out.println("Dive #3\nAdding Depth: 10");
        testDives.add(10);
        assertTrue("Depth was not added correctly",testDives.get(6) == 10);
        System.out.println("Adding Time: 11");
        testDives.add(11);
        assertTrue("Time was not added correctly",testDives.get(7) == 11);
        System.out.println("Adding Surface Interval Time: 12");
        testDives.add(12);
        assertTrue("Surface Interval Time was not added correctly",testDives.get(8) == 12);

        DiveTable dt = new DiveTable();
        dt.calculateDiveTableResults(testDives);
        System.out.println(dt);
       
        //Testing Dive Plan 2 (Illegal)
        System.out.println("\n\nTest Dive Plan 2...");
        List<Integer> testDives2 = new ArrayList<Integer>();

        System.out.println("Dive #1\nAdding Depth: 50");
        testDives2.add(50);
        assertTrue("Depth was not added correctly",testDives2.get(0) == 50);
        System.out.println("Adding Time: 55");
        testDives2.add(55);
        assertTrue("Time was not added correctly",testDives2.get(1) == 55);
        System.out.println("Adding Surface Interval Time: 110");
        testDives2.add(110);
        assertTrue("Surface Interval Time was not added correctly",testDives2.get(2) == 110);

        System.out.println("Dive #2\nAdding Depth: 50");
        testDives2.add(50);
        assertTrue("Depth was not added correctly",testDives2.get(3) == 50);
        System.out.println("Adding Time: 51");
        testDives2.add(51);
        assertTrue("Time was not added correctly",testDives2.get(4) == 51);
        System.out.println("Adding Surface Interval Time: 52");
        testDives2.add(52);
        assertTrue("Surface Interval Time was not added correctly",testDives2.get(5) == 52);

        System.out.println("Dive #3\nAdding Depth: 10");
        testDives2.add(10);
        assertTrue("Depth was not added correctly",testDives2.get(6) == 10);
        System.out.println("Adding Time: 11");
        testDives2.add(11);
        assertTrue("Time was not added correctly",testDives2.get(7) == 11);
        System.out.println("Adding Surface Interval Time: 12\n");
        testDives2.add(12);
        assertTrue("Surface Interval Time was not added correctly",testDives2.get(8) == 12);

        DiveTable dt2 = new DiveTable();
        dt2.calculateDiveTableResults(testDives2);
        System.out.println(dt2);
	}
	
}
