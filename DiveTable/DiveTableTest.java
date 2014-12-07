import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.experimental.results.PrintableResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Set;


/**
 * DiveTableTest
 * @author Chris Caoagdan
 *
 */

public class DiveTableTest {

	//private final String makePlain = "\033[0;0m";
	//private final String makeBold = "\033[0;1m";
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void checkDiveTableIfNotNull() {
		System.out.println("\"Test 2: Checking that maps aren't null\"");
		DiveTable diveTable = new DiveTable();
		assertNotNull("'endOfDiveTable' is null.", diveTable.endOfDiveTable);
		if (diveTable.endOfDiveTable != null) {
			System.out.println("assertNotNull: 'endOfDiveTable' is OK.");
		}
		assertNotNull("'repetitiveDiveTimeTable' is null.", diveTable.repetitiveDiveTimeTable);
		if (diveTable.repetitiveDiveTimeTable != null) {
			System.out.println("assertNotNull: 'repetitiveDiveTimeTable' is OK.");
		}
		assertNotNull("'surfaceIntervalTimeTable' is null.", diveTable.surfaceIntervalTimeTable);
		if (diveTable.surfaceIntervalTimeTable != null) {
			System.out.println("assertNotNull: 'surfaceIntervalTimeTable' is OK.\n");
		}
	}
	
	@Test
	//Note that most of this test was copied/transfered from the DiveTable class
	public void checkDiveInputs() { 
		System.out.println("\"Test 3: Checking if information is added correctly\"");
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
        //dt.calculateDiveTableResults();
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
	
	@Test
	public void checkEndOfDiveTable() {
		System.out.println("\"Test 1: Checking endOfDiveTable Data\"");
		DiveTable diveTableCheck = new DiveTable();
		
		/*Checking Keys of endOfDiveTable*/
		//Row 40
		assertTrue("Row 40 Size is not valid; Supposed to have 12 Keys", diveTableCheck.endOfDiveTable.get(40).keySet().size() == 12); //Check row size
		//Key 5
		assertTrue("Row 40 is not valid; Key is supposed to be 5", diveTableCheck.endOfDiveTable.get(40).containsKey(5));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be A", diveTableCheck.endOfDiveTable.get(40).get(5).pressureGroup == "A");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(5).decompressionTime == 0);
		//Key 15
		assertTrue("Row 40 is not valid; Key is supposed to be 15", diveTableCheck.endOfDiveTable.get(40).containsKey(15));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be B", diveTableCheck.endOfDiveTable.get(40).get(15).pressureGroup == "B");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(15).decompressionTime == 0);
		//Key 25
		assertTrue("Row 40 is not valid; Key is supposed to be 25", diveTableCheck.endOfDiveTable.get(40).containsKey(25));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be C", diveTableCheck.endOfDiveTable.get(40).get(25).pressureGroup == "C");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(25).decompressionTime == 0);
		
		assertTrue("Row 40 is not valid; Key is supposed to be 30", diveTableCheck.endOfDiveTable.get(40).containsKey(30));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be D", diveTableCheck.endOfDiveTable.get(40).get(30).pressureGroup == "D");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(30).decompressionTime == 0);
		
		assertTrue("Row 40 is not valid; Key is supposed to be 40", diveTableCheck.endOfDiveTable.get(40).containsKey(40));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be E", diveTableCheck.endOfDiveTable.get(40).get(40).pressureGroup == "E");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(40).decompressionTime == 0);
		
		assertTrue("Row 40 is not valid; Key is supposed to be 50", diveTableCheck.endOfDiveTable.get(40).containsKey(50));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be F", diveTableCheck.endOfDiveTable.get(40).get(50).pressureGroup == "F");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(15).decompressionTime == 0);
		
		assertTrue("Row 40 is not valid; Key is supposed to be 70", diveTableCheck.endOfDiveTable.get(40).containsKey(70));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be G", diveTableCheck.endOfDiveTable.get(40).get(70).pressureGroup == "G");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(70).decompressionTime == 0);
		
		assertTrue("Row 40 is not valid; Key is supposed to be 80", diveTableCheck.endOfDiveTable.get(40).containsKey(80));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be H", diveTableCheck.endOfDiveTable.get(40).get(80).pressureGroup == "H");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(80).decompressionTime == 0);
		
		assertTrue("Row 40 is not valid; Key is supposed to be 100", diveTableCheck.endOfDiveTable.get(40).containsKey(100));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be I", diveTableCheck.endOfDiveTable.get(40).get(100).pressureGroup == "I");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(100).decompressionTime == 0);
		
		assertTrue("Row 40 is not valid; Key is supposed to be 110", diveTableCheck.endOfDiveTable.get(40).containsKey(110));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be J", diveTableCheck.endOfDiveTable.get(40).get(110).pressureGroup == "J");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(110).decompressionTime == 0);
		
		assertTrue("Row 40 is not valid; Key is supposed to be 130", diveTableCheck.endOfDiveTable.get(40).containsKey(130));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be K", diveTableCheck.endOfDiveTable.get(40).get(130).pressureGroup == "K");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(40).get(130).decompressionTime == 0);
		
		assertTrue("Row 40 is not valid; Key is supposed to be 150", diveTableCheck.endOfDiveTable.get(40).containsKey(150));
		assertTrue("Row 40 is not valid; Pressure Group is supposed to be L", diveTableCheck.endOfDiveTable.get(40).get(150).pressureGroup == "L");
		assertTrue("Row 40 is not valid; Decompression Time is supposed to be 5", diveTableCheck.endOfDiveTable.get(40).get(150).decompressionTime == 5);
		
		// --Row 50--
		assertTrue("Row 50 Size is not valid; Supposed to have 10 Keys", diveTableCheck.endOfDiveTable.get(50).keySet().size() == 10);
		assertTrue("Row 50 is not valid; Key is supposed to be 10", diveTableCheck.endOfDiveTable.get(50).containsKey(10));
		assertTrue("Row 50 is not valid; Pressure Group is supposed to be B", diveTableCheck.endOfDiveTable.get(50).get(10).pressureGroup == "B");
		assertTrue("Row 50 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(50).get(10).decompressionTime == 0);
		
		assertTrue("Row 50 is not valid; Key is supposed to be 15", diveTableCheck.endOfDiveTable.get(50).containsKey(15));
		assertTrue("Row 50 is not valid; Pressure Group is supposed to be C", diveTableCheck.endOfDiveTable.get(50).get(15).pressureGroup == "C");
		assertTrue("Row 50 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(50).get(15).decompressionTime == 0);
		
		assertTrue("Row 50 is not valid; Key is supposed to be 25", diveTableCheck.endOfDiveTable.get(50).containsKey(25));
		assertTrue("Row 50 is not valid; Pressure Group is supposed to be D", diveTableCheck.endOfDiveTable.get(50).get(25).pressureGroup == "D");
		assertTrue("Row 50 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(50).get(25).decompressionTime == 0);
		
		assertTrue("Row 50 is not valid; Key is supposed to be 30", diveTableCheck.endOfDiveTable.get(50).containsKey(30));
		assertTrue("Row 50 is not valid; Pressure Group is supposed to be E", diveTableCheck.endOfDiveTable.get(50).get(30).pressureGroup == "E");
		assertTrue("Row 50 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(50).get(30).decompressionTime == 0);
		
		assertTrue("Row 50 is not valid; Key is supposed to be 40", diveTableCheck.endOfDiveTable.get(50).containsKey(40));
		assertTrue("Row 50 is not valid; Pressure Group is supposed to be F", diveTableCheck.endOfDiveTable.get(50).get(40).pressureGroup == "F");
		assertTrue("Row 50 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(50).get(40).decompressionTime == 0);
		
		assertTrue("Row 50 is not valid; Key is supposed to be 50", diveTableCheck.endOfDiveTable.get(50).containsKey(50));
		assertTrue("Row 50 is not valid; Pressure Group is supposed to be G", diveTableCheck.endOfDiveTable.get(50).get(50).pressureGroup == "G");
		assertTrue("Row 50 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(50).get(50).decompressionTime == 0);
		
		assertTrue("Row 50 is not valid; Key is supposed to be 60", diveTableCheck.endOfDiveTable.get(50).containsKey(60));
		assertTrue("Row 50 is not valid; Pressure Group is supposed to be H", diveTableCheck.endOfDiveTable.get(50).get(60).pressureGroup == "H");
		assertTrue("Row 50 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(50).get(60).decompressionTime == 0);
		
		assertTrue("Row 50 is not valid; Key is supposed to be 70", diveTableCheck.endOfDiveTable.get(50).containsKey(70));
		assertTrue("Row 50 is not valid; Pressure Group is supposed to be I", diveTableCheck.endOfDiveTable.get(50).get(70).pressureGroup == "I");
		assertTrue("Row 50 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(50).get(70).decompressionTime == 0);
		
		assertTrue("Row 50 is not valid; Key is supposed to be 80", diveTableCheck.endOfDiveTable.get(50).containsKey(80));
		assertTrue("Row 50 is not valid; Pressure Group is supposed to be J", diveTableCheck.endOfDiveTable.get(50).get(80).pressureGroup == "J");
		assertTrue("Row 50 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(50).get(80).decompressionTime == 0);
		
		assertTrue("Row 50 is not valid; Key is supposed to be 100", diveTableCheck.endOfDiveTable.get(50).containsKey(100));
		assertTrue("Row 50 is not valid; Pressure Group is supposed to be L", diveTableCheck.endOfDiveTable.get(50).get(100).pressureGroup == "L");
		assertTrue("Row 50 is not valid; Decompression Time is supposed to be 5", diveTableCheck.endOfDiveTable.get(50).get(100).decompressionTime == 5);
		
		// --Row 60--
		assertTrue("Row 60 Size is not valid; Supposed to have 10 Keys", diveTableCheck.endOfDiveTable.get(60).keySet().size() == 10);
		assertTrue("Row 60 is not valid; Key is supposed to be 10", diveTableCheck.endOfDiveTable.get(60).containsKey(10));
		assertTrue("Row 60 is not valid; Pressure Group is supposed to be B", diveTableCheck.endOfDiveTable.get(60).get(10).pressureGroup == "B");
		assertTrue("Row 60 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(60).get(10).decompressionTime == 0);
		
		assertTrue("Row 60 is not valid; Key is supposed to be 15", diveTableCheck.endOfDiveTable.get(60).containsKey(15));
		assertTrue("Row 60 is not valid; Pressure Group is supposed to be C", diveTableCheck.endOfDiveTable.get(60).get(15).pressureGroup == "C");
		assertTrue("Row 60 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(60).get(15).decompressionTime == 0);
		
		assertTrue("Row 60 is not valid; Key is supposed to be 20", diveTableCheck.endOfDiveTable.get(60).containsKey(20));
		assertTrue("Row 60 is not valid; Pressure Group is supposed to be D", diveTableCheck.endOfDiveTable.get(60).get(20).pressureGroup == "D");
		assertTrue("Row 60 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(60).get(20).decompressionTime == 0);
		
		assertTrue("Row 60 is not valid; Key is supposed to be 25", diveTableCheck.endOfDiveTable.get(60).containsKey(25));
		assertTrue("Row 60 is not valid; Pressure Group is supposed to be E", diveTableCheck.endOfDiveTable.get(60).get(25).pressureGroup == "E");
		assertTrue("Row 60 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(60).get(25).decompressionTime == 0);
		
		assertTrue("Row 60 is not valid; Key is supposed to be 30", diveTableCheck.endOfDiveTable.get(60).containsKey(30));
		assertTrue("Row 60 is not valid; Pressure Group is supposed to be F", diveTableCheck.endOfDiveTable.get(60).get(30).pressureGroup == "F");
		assertTrue("Row 60 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(60).get(30).decompressionTime == 0);
		
		assertTrue("Row 60 is not valid; Key is supposed to be 40", diveTableCheck.endOfDiveTable.get(60).containsKey(40));
		assertTrue("Row 60 is not valid; Pressure Group is supposed to be G", diveTableCheck.endOfDiveTable.get(60).get(40).pressureGroup == "G");
		assertTrue("Row 60 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(60).get(40).decompressionTime == 0);
		
		assertTrue("Row 60 is not valid; Key is supposed to be 50", diveTableCheck.endOfDiveTable.get(60).containsKey(50));
		assertTrue("Row 60 is not valid; Pressure Group is supposed to be H", diveTableCheck.endOfDiveTable.get(60).get(50).pressureGroup == "H");
		assertTrue("Row 60 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(60).get(50).decompressionTime == 0);
		
		assertTrue("Row 60 is not valid; Key is supposed to be 55", diveTableCheck.endOfDiveTable.get(60).containsKey(55));
		assertTrue("Row 60 is not valid; Pressure Group is supposed to be I", diveTableCheck.endOfDiveTable.get(60).get(55).pressureGroup == "I");
		assertTrue("Row 60 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(60).get(55).decompressionTime == 0);
		
		assertTrue("Row 60 is not valid; Key is supposed to be 60", diveTableCheck.endOfDiveTable.get(60).containsKey(60));
		assertTrue("Row 60 is not valid; Pressure Group is supposed to be J", diveTableCheck.endOfDiveTable.get(60).get(60).pressureGroup == "J");
		assertTrue("Row 60 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(60).get(60).decompressionTime == 5);
		
		assertTrue("Row 60 is not valid; Key is supposed to be 80", diveTableCheck.endOfDiveTable.get(60).containsKey(80));
		assertTrue("Row 60 is not valid; Pressure Group is supposed to be L", diveTableCheck.endOfDiveTable.get(60).get(80).pressureGroup == "L");
		assertTrue("Row 60 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(60).get(80).decompressionTime == 7);
	
		// --Row 70--
		assertTrue("Row 70 Size is not valid; Supposed to have 11 Keys", diveTableCheck.endOfDiveTable.get(70).keySet().size() == 11);
		assertTrue("Row 70 is not valid; Key is supposed to be 5", diveTableCheck.endOfDiveTable.get(70).containsKey(5));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be B", diveTableCheck.endOfDiveTable.get(70).get(5).pressureGroup == "B");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(5).decompressionTime == 0);
		
		assertTrue("Row 70 is not valid; Key is supposed to be 10", diveTableCheck.endOfDiveTable.get(70).containsKey(10));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be C", diveTableCheck.endOfDiveTable.get(70).get(10).pressureGroup == "C");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(10).decompressionTime == 0);
		
		assertTrue("Row 70 is not valid; Key is supposed to be 15", diveTableCheck.endOfDiveTable.get(70).containsKey(15));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be D", diveTableCheck.endOfDiveTable.get(70).get(15).pressureGroup == "D");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(15).decompressionTime == 0);
		
		assertTrue("Row 70 is not valid; Key is supposed to be 20", diveTableCheck.endOfDiveTable.get(70).containsKey(20));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be E", diveTableCheck.endOfDiveTable.get(70).get(20).pressureGroup == "E");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(20).decompressionTime == 0);
		
		assertTrue("Row 70 is not valid; Key is supposed to be 30", diveTableCheck.endOfDiveTable.get(70).containsKey(30));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be F", diveTableCheck.endOfDiveTable.get(70).get(30).pressureGroup == "F");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(30).decompressionTime == 0);
		
		assertTrue("Row 70 is not valid; Key is supposed to be 35", diveTableCheck.endOfDiveTable.get(70).containsKey(35));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be G", diveTableCheck.endOfDiveTable.get(70).get(35).pressureGroup == "G");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(35).decompressionTime == 0);
		
		assertTrue("Row 70 is not valid; Key is supposed to be 40", diveTableCheck.endOfDiveTable.get(70).containsKey(40));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be H", diveTableCheck.endOfDiveTable.get(70).get(40).pressureGroup == "H");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(40).decompressionTime == 0);
		
		assertTrue("Row 70 is not valid; Key is supposed to be 45", diveTableCheck.endOfDiveTable.get(70).containsKey(45));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be I", diveTableCheck.endOfDiveTable.get(70).get(45).pressureGroup == "I");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(45).decompressionTime == 0);
		
		assertTrue("Row 70 is not valid; Key is supposed to be 50", diveTableCheck.endOfDiveTable.get(70).containsKey(50));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be J", diveTableCheck.endOfDiveTable.get(70).get(50).pressureGroup == "J");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(50).decompressionTime == 5);
		
		assertTrue("Row 70 is not valid; Key is supposed to be 60", diveTableCheck.endOfDiveTable.get(70).containsKey(60));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be K", diveTableCheck.endOfDiveTable.get(70).get(60).pressureGroup == "K");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(60).decompressionTime == 8);
		
		assertTrue("Row 70 is not valid; Key is supposed to be 70", diveTableCheck.endOfDiveTable.get(70).containsKey(70));
		assertTrue("Row 70 is not valid; Pressure Group is supposed to be L", diveTableCheck.endOfDiveTable.get(70).get(70).pressureGroup == "L");
		assertTrue("Row 70 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(70).get(70).decompressionTime == 14);
		
		// --Row 80--
		assertTrue("Row 80 Size is not valid; Supposed to have 10 Keys", diveTableCheck.endOfDiveTable.get(80).keySet().size() == 10);
		assertTrue("Row 80 is not valid; Key is supposed to be 5", diveTableCheck.endOfDiveTable.get(80).containsKey(5));
		assertTrue("Row 80 is not valid; Pressure Group is supposed to be B", diveTableCheck.endOfDiveTable.get(80).get(5).pressureGroup == "B");
		assertTrue("Row 80 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(80).get(5).decompressionTime == 0);
		
		assertTrue("Row 80 is not valid; Key is supposed to be 10", diveTableCheck.endOfDiveTable.get(80).containsKey(10));
		assertTrue("Row 80 is not valid; Pressure Group is supposed to be C", diveTableCheck.endOfDiveTable.get(80).get(10).pressureGroup == "C");
		assertTrue("Row 80 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(80).get(10).decompressionTime == 0);
		
		assertTrue("Row 80 is not valid; Key is supposed to be 15", diveTableCheck.endOfDiveTable.get(80).containsKey(15));
		assertTrue("Row 80 is not valid; Pressure Group is supposed to be D", diveTableCheck.endOfDiveTable.get(80).get(15).pressureGroup == "D");
		assertTrue("Row 80 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(80).get(15).decompressionTime == 0);
		
		assertTrue("Row 80 is not valid; Key is supposed to be 20", diveTableCheck.endOfDiveTable.get(80).containsKey(20));
		assertTrue("Row 80 is not valid; Pressure Group is supposed to be E", diveTableCheck.endOfDiveTable.get(80).get(20).pressureGroup == "E");
		assertTrue("Row 80 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(80).get(20).decompressionTime == 0);
		
		assertTrue("Row 80 is not valid; Key is supposed to be 25", diveTableCheck.endOfDiveTable.get(80).containsKey(25));
		assertTrue("Row 80 is not valid; Pressure Group is supposed to be F", diveTableCheck.endOfDiveTable.get(80).get(25).pressureGroup == "F");
		assertTrue("Row 80 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(80).get(25).decompressionTime == 0);
		
		assertTrue("Row 80 is not valid; Key is supposed to be 30", diveTableCheck.endOfDiveTable.get(80).containsKey(30));
		assertTrue("Row 80 is not valid; Pressure Group is supposed to be G", diveTableCheck.endOfDiveTable.get(80).get(30).pressureGroup == "G");
		assertTrue("Row 80 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(80).get(30).decompressionTime == 0);
		
		assertTrue("Row 80 is not valid; Key is supposed to be 35", diveTableCheck.endOfDiveTable.get(80).containsKey(35));
		assertTrue("Row 80 is not valid; Pressure Group is supposed to be H", diveTableCheck.endOfDiveTable.get(80).get(35).pressureGroup == "H");
		assertTrue("Row 80 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(80).get(35).decompressionTime == 0);
		
		assertTrue("Row 80 is not valid; Key is supposed to be 40", diveTableCheck.endOfDiveTable.get(80).containsKey(40));
		assertTrue("Row 80 is not valid; Pressure Group is supposed to be I", diveTableCheck.endOfDiveTable.get(80).get(40).pressureGroup == "I");
		assertTrue("Row 80 is not valid; Decompression Time is supposed to be 5", diveTableCheck.endOfDiveTable.get(80).get(40).decompressionTime == 5);
		
		assertTrue("Row 80 is not valid; Key is supposed to be 50", diveTableCheck.endOfDiveTable.get(80).containsKey(50));
		assertTrue("Row 80 is not valid; Pressure Group is supposed to be K", diveTableCheck.endOfDiveTable.get(80).get(50).pressureGroup == "K");
		assertTrue("Row 80 is not valid; Decompression Time is supposed to be 10", diveTableCheck.endOfDiveTable.get(80).get(50).decompressionTime == 10);
		
		assertTrue("Row 80 is not valid; Key is supposed to be 60", diveTableCheck.endOfDiveTable.get(80).containsKey(60));
		assertTrue("Row 80 is not valid; Pressure Group is supposed to be L", diveTableCheck.endOfDiveTable.get(80).get(60).pressureGroup == "L");
		assertTrue("Row 80 is not valid; Decompression Time is supposed to be 17", diveTableCheck.endOfDiveTable.get(80).get(60).decompressionTime == 17);
		
		// --Row 90--
		assertTrue("Row 90 Size is not valid; Supposed to have 9 Keys", diveTableCheck.endOfDiveTable.get(90).keySet().size() == 9);
		assertTrue("Row 90 is not valid; Key is supposed to be 5", diveTableCheck.endOfDiveTable.get(90).containsKey(5));
		assertTrue("Row 90 is not valid; Pressure Group is supposed to be B", diveTableCheck.endOfDiveTable.get(90).get(5).pressureGroup == "B");
		assertTrue("Row 90 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(90).get(5).decompressionTime == 0);
		
		assertTrue("Row 90 is not valid; Key is supposed to be 10", diveTableCheck.endOfDiveTable.get(90).containsKey(10));
		assertTrue("Row 90 is not valid; Pressure Group is supposed to be C", diveTableCheck.endOfDiveTable.get(90).get(10).pressureGroup == "C");
		assertTrue("Row 90 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(90).get(10).decompressionTime == 0);
		
		assertTrue("Row 90 is not valid; Key is supposed to be 12", diveTableCheck.endOfDiveTable.get(90).containsKey(12));
		assertTrue("Row 90 is not valid; Pressure Group is supposed to be D", diveTableCheck.endOfDiveTable.get(90).get(12).pressureGroup == "D");
		assertTrue("Row 90 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(90).get(12).decompressionTime == 0);
		
		assertTrue("Row 90 is not valid; Key is supposed to be 15", diveTableCheck.endOfDiveTable.get(90).containsKey(15));
		assertTrue("Row 90 is not valid; Pressure Group is supposed to be E", diveTableCheck.endOfDiveTable.get(90).get(15).pressureGroup == "E");
		assertTrue("Row 90 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(90).get(15).decompressionTime == 0);
		
		assertTrue("Row 90 is not valid; Key is supposed to be 20", diveTableCheck.endOfDiveTable.get(90).containsKey(20));
		assertTrue("Row 90 is not valid; Pressure Group is supposed to be F", diveTableCheck.endOfDiveTable.get(90).get(20).pressureGroup == "F");
		assertTrue("Row 90 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(90).get(20).decompressionTime == 0);
		
		assertTrue("Row 90 is not valid; Key is supposed to be 25", diveTableCheck.endOfDiveTable.get(90).containsKey(25));
		assertTrue("Row 90 is not valid; Pressure Group is supposed to be G", diveTableCheck.endOfDiveTable.get(90).get(25).pressureGroup == "G");
		assertTrue("Row 90 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(90).get(25).decompressionTime == 0);
		
		assertTrue("Row 90 is not valid; Key is supposed to be 30", diveTableCheck.endOfDiveTable.get(90).containsKey(30));
		assertTrue("Row 90 is not valid; Pressure Group is supposed to be H", diveTableCheck.endOfDiveTable.get(90).get(30).pressureGroup == "H");
		assertTrue("Row 90 is not valid; Decompression Time is supposed to be 5", diveTableCheck.endOfDiveTable.get(90).get(30).decompressionTime == 5);
		
		assertTrue("Row 90 is not valid; Key is supposed to be 40", diveTableCheck.endOfDiveTable.get(90).containsKey(40));
		assertTrue("Row 90 is not valid; Pressure Group is supposed to be J", diveTableCheck.endOfDiveTable.get(90).get(40).pressureGroup == "J");
		assertTrue("Row 90 is not valid; Decompression Time is supposed to be 7", diveTableCheck.endOfDiveTable.get(90).get(40).decompressionTime == 7);
		
		assertTrue("Row 90 is not valid; Key is supposed to be 50", diveTableCheck.endOfDiveTable.get(90).containsKey(50));
		assertTrue("Row 90 is not valid; Pressure Group is supposed to be L", diveTableCheck.endOfDiveTable.get(90).get(50).pressureGroup == "L");
		assertTrue("Row 90 is not valid; Decompression Time is supposed to be 18", diveTableCheck.endOfDiveTable.get(90).get(50).decompressionTime == 18);
		
		// --Row 100--
		assertTrue("Row 100 Size is not valid; Supposed to have 8 Keys", diveTableCheck.endOfDiveTable.get(100).keySet().size() == 8);
		assertTrue("Row 100 is not valid; Key is supposed to be 5", diveTableCheck.endOfDiveTable.get(100).containsKey(5));
		assertTrue("Row 100 is not valid; Pressure Group is supposed to be B", diveTableCheck.endOfDiveTable.get(100).get(5).pressureGroup == "B");
		assertTrue("Row 100 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(100).get(5).decompressionTime == 0);
		
		assertTrue("Row 100 is not valid; Key is supposed to be 7", diveTableCheck.endOfDiveTable.get(100).containsKey(7));
		assertTrue("Row 100 is not valid; Pressure Group is supposed to be C", diveTableCheck.endOfDiveTable.get(100).get(7).pressureGroup == "C");
		assertTrue("Row 100 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(100).get(7).decompressionTime == 0);
		
		assertTrue("Row 100 is not valid; Key is supposed to be 10", diveTableCheck.endOfDiveTable.get(100).containsKey(10));
		assertTrue("Row 100 is not valid; Pressure Group is supposed to be D", diveTableCheck.endOfDiveTable.get(100).get(10).pressureGroup == "D");
		assertTrue("Row 100 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(100).get(10).decompressionTime == 0);
		
		assertTrue("Row 100 is not valid; Key is supposed to be 15", diveTableCheck.endOfDiveTable.get(100).containsKey(15));
		assertTrue("Row 100 is not valid; Pressure Group is supposed to be E", diveTableCheck.endOfDiveTable.get(100).get(15).pressureGroup == "E");
		assertTrue("Row 100 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(100).get(15).decompressionTime == 0);
		
		assertTrue("Row 100 is not valid; Key is supposed to be 20", diveTableCheck.endOfDiveTable.get(100).containsKey(20));
		assertTrue("Row 100 is not valid; Pressure Group is supposed to be F", diveTableCheck.endOfDiveTable.get(100).get(20).pressureGroup == "F");
		assertTrue("Row 100 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(100).get(20).decompressionTime == 0);
		
		assertTrue("Row 100 is not valid; Key is supposed to be 22", diveTableCheck.endOfDiveTable.get(100).containsKey(22));
		assertTrue("Row 100 is not valid; Pressure Group is supposed to be G", diveTableCheck.endOfDiveTable.get(100).get(22).pressureGroup == "G");
		assertTrue("Row 100 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(100).get(22).decompressionTime == 0);
		
		assertTrue("Row 100 is not valid; Key is supposed to be 25", diveTableCheck.endOfDiveTable.get(100).containsKey(25));
		assertTrue("Row 100 is not valid; Pressure Group is supposed to be H", diveTableCheck.endOfDiveTable.get(100).get(25).pressureGroup == "H");
		assertTrue("Row 100 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(100).get(25).decompressionTime == 5);
		
		assertTrue("Row 100 is not valid; Key is supposed to be 40", diveTableCheck.endOfDiveTable.get(100).containsKey(40));
		assertTrue("Row 100 is not valid; Pressure Group is supposed to be K", diveTableCheck.endOfDiveTable.get(100).get(40).pressureGroup == "K");
		assertTrue("Row 100 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(100).get(40).decompressionTime == 15);
		
		// --Row 110--
		assertTrue("Row 110 Size is not valid; Supposed to have 6 Keys", diveTableCheck.endOfDiveTable.get(110).keySet().size() == 6);
		assertTrue("Row 110 is not valid; Key is supposed to be 5", diveTableCheck.endOfDiveTable.get(110).containsKey(5));
		assertTrue("Row 110 is not valid; Pressure Group is supposed to be C", diveTableCheck.endOfDiveTable.get(110).get(5).pressureGroup == "C");
		assertTrue("Row 110 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(110).get(5).decompressionTime == 0);
		
		assertTrue("Row 110 is not valid; Key is supposed to be 10", diveTableCheck.endOfDiveTable.get(110).containsKey(10));
		assertTrue("Row 110 is not valid; Pressure Group is supposed to be D", diveTableCheck.endOfDiveTable.get(110).get(10).pressureGroup == "D");
		assertTrue("Row 110 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(110).get(10).decompressionTime == 0);
		
		assertTrue("Row 110 is not valid; Key is supposed to be 13", diveTableCheck.endOfDiveTable.get(110).containsKey(13));
		assertTrue("Row 110 is not valid; Pressure Group is supposed to be E", diveTableCheck.endOfDiveTable.get(110).get(13).pressureGroup == "E");
		assertTrue("Row 110 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(110).get(13).decompressionTime == 0);
		
		assertTrue("Row 110 is not valid; Key is supposed to be 15", diveTableCheck.endOfDiveTable.get(110).containsKey(15));
		assertTrue("Row 110 is not valid; Pressure Group is supposed to be F", diveTableCheck.endOfDiveTable.get(110).get(15).pressureGroup == "F");
		assertTrue("Row 110 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(110).get(15).decompressionTime == 0);
		
		assertTrue("Row 110 is not valid; Key is supposed to be 20", diveTableCheck.endOfDiveTable.get(110).containsKey(20));
		assertTrue("Row 110 is not valid; Pressure Group is supposed to be G", diveTableCheck.endOfDiveTable.get(110).get(20).pressureGroup == "G");
		assertTrue("Row 110 is not valid; Decompression Time is supposed to be 5", diveTableCheck.endOfDiveTable.get(110).get(20).decompressionTime == 5);
		
		assertTrue("Row 110 is not valid; Key is supposed to be 30", diveTableCheck.endOfDiveTable.get(110).containsKey(30));
		assertTrue("Row 110 is not valid; Pressure Group is supposed to be J", diveTableCheck.endOfDiveTable.get(110).get(30).pressureGroup == "J");
		assertTrue("Row 110 is not valid; Decompression Time is supposed to be 7", diveTableCheck.endOfDiveTable.get(110).get(30).decompressionTime == 7);
		
		// --Row 120--
		assertTrue("Row 120 Size is not valid; Supposed to have 6 Keys", diveTableCheck.endOfDiveTable.get(120).keySet().size() == 6);
		assertTrue("Row 120 is not valid; Key is supposed to be 5", diveTableCheck.endOfDiveTable.get(120).containsKey(5));
		assertTrue("Row 120 is not valid; Pressure Group is supposed to be C", diveTableCheck.endOfDiveTable.get(120).get(5).pressureGroup == "C");
		assertTrue("Row 120 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(120).get(5).decompressionTime == 0);
		
		assertTrue("Row 120 is not valid; Key is supposed to be 10", diveTableCheck.endOfDiveTable.get(120).containsKey(10));
		assertTrue("Row 120 is not valid; Pressure Group is supposed to be D", diveTableCheck.endOfDiveTable.get(120).get(10).pressureGroup == "D");
		assertTrue("Row 120 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(120).get(10).decompressionTime == 0);
		
		assertTrue("Row 120 is not valid; Key is supposed to be 12", diveTableCheck.endOfDiveTable.get(120).containsKey(12));
		assertTrue("Row 120 is not valid; Pressure Group is supposed to be E", diveTableCheck.endOfDiveTable.get(120).get(12).pressureGroup == "E");
		assertTrue("Row 120 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(120).get(12).decompressionTime == 0);
		
		assertTrue("Row 120 is not valid; Key is supposed to be 15", diveTableCheck.endOfDiveTable.get(120).containsKey(15));
		assertTrue("Row 120 is not valid; Pressure Group is supposed to be F", diveTableCheck.endOfDiveTable.get(120).get(15).pressureGroup == "F");
		assertTrue("Row 120 is not valid; Decompression Time is supposed to be 5", diveTableCheck.endOfDiveTable.get(120).get(15).decompressionTime == 5);
		
		assertTrue("Row 120 is not valid; Key is supposed to be 25", diveTableCheck.endOfDiveTable.get(120).containsKey(25));
		assertTrue("Row 120 is not valid; Pressure Group is supposed to be I", diveTableCheck.endOfDiveTable.get(120).get(25).pressureGroup == "I");
		assertTrue("Row 120 is not valid; Decompression Time is supposed to be 6", diveTableCheck.endOfDiveTable.get(120).get(25).decompressionTime == 6);
		
		assertTrue("Row 120 is not valid; Key is supposed to be 30", diveTableCheck.endOfDiveTable.get(120).containsKey(30));
		assertTrue("Row 120 is not valid; Pressure Group is supposed to be J", diveTableCheck.endOfDiveTable.get(120).get(30).pressureGroup == "J");
		assertTrue("Row 120 is not valid; Decompression Time is supposed to be 14", diveTableCheck.endOfDiveTable.get(120).get(30).decompressionTime == 14);
		
		// --Row 130--
		assertTrue("Row 130 Size is not valid; Supposed to have 4 Keys", diveTableCheck.endOfDiveTable.get(130).keySet().size() == 4);
		assertTrue("Row 130 is not valid; Key is supposed to be 5", diveTableCheck.endOfDiveTable.get(130).containsKey(5));
		assertTrue("Row 130 is not valid; Pressure Group is supposed to be C", diveTableCheck.endOfDiveTable.get(130).get(5).pressureGroup == "C");
		assertTrue("Row 130 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(130).get(5).decompressionTime == 0);
		
		assertTrue("Row 130 is not valid; Key is supposed to be 8", diveTableCheck.endOfDiveTable.get(130).containsKey(8));
		assertTrue("Row 130 is not valid; Pressure Group is supposed to be D", diveTableCheck.endOfDiveTable.get(130).get(8).pressureGroup == "D");
		assertTrue("Row 130 is not valid; Decompression Time is supposed to be 0", diveTableCheck.endOfDiveTable.get(130).get(8).decompressionTime == 0);
		
		assertTrue("Row 130 is not valid; Key is supposed to be 10", diveTableCheck.endOfDiveTable.get(130).containsKey(10));
		assertTrue("Row 130 is not valid; Pressure Group is supposed to be E", diveTableCheck.endOfDiveTable.get(130).get(10).pressureGroup == "E");
		assertTrue("Row 130 is not valid; Decompression Time is supposed to be 5", diveTableCheck.endOfDiveTable.get(130).get(10).decompressionTime == 5);
		
		assertTrue("Row 130 is not valid; Key is supposed to be 25", diveTableCheck.endOfDiveTable.get(130).containsKey(25));
		assertTrue("Row 130 is not valid; Pressure Group is supposed to be J", diveTableCheck.endOfDiveTable.get(130).get(25).pressureGroup == "J");
		assertTrue("Row 130 is not valid; Decompression Time is supposed to be 10", diveTableCheck.endOfDiveTable.get(130).get(25).decompressionTime == 10);
	}
	
	@Test
	public void checkSITtable() {
		DiveTable diveTableCheck = new DiveTable();
		System.out.println("\n\"Test 4: Checking surfaceIntervalTimeTable Data\"");
		/*Checking surfaceIntervalTimeTable*/
		assertTrue("Column A Size is invalid; Supposed to have 1 Key", diveTableCheck.surfaceIntervalTimeTable.get("A").size() == 1);
		assertTrue("Column A is invalid; Key is supposed to be 10, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("A").get(10).equals("A"));
		
		assertTrue("Column B Size is invalid; Supposed to have 2 Keys", diveTableCheck.surfaceIntervalTimeTable.get("B").size() == 2);
		assertTrue("Column B is invalid; Key is supposed to be 201, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("B").get(201).equals("A"));
		assertTrue("Column B is invalid; Key is supposed to be 10, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("B").get(10).equals("B"));
		
		assertTrue("Column C Size is invalid; Supposed to have 3 Keys", diveTableCheck.surfaceIntervalTimeTable.get("C").size() == 3);
		assertTrue("Column C is invalid; Key is supposed to be 201, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("C").get(290).equals("A"));
		assertTrue("Column C is invalid; Key is supposed to be 100, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("C").get(100).equals("B"));
		assertTrue("Column C is invalid; Key is supposed to be 10, Value supposed to be C", diveTableCheck.surfaceIntervalTimeTable.get("C").get(10).equals("C"));
		
		assertTrue("Column D Size is invalid; Supposed to have 4 Keys", diveTableCheck.surfaceIntervalTimeTable.get("D").size() == 4);
		assertTrue("Column D is invalid; Key is supposed to be 349, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("D").get(349).equals("A"));
		assertTrue("Column D is invalid; Key is supposed to be 159, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("D").get(159).equals("B"));
		assertTrue("Column D is invalid; Key is supposed to be 70, Value supposed to be C", diveTableCheck.surfaceIntervalTimeTable.get("D").get(70).equals("C"));
		assertTrue("Column D is invalid; Key is supposed to be 10, Value supposed to be D", diveTableCheck.surfaceIntervalTimeTable.get("D").get(10).equals("D"));
		
		assertTrue("Column E Size is invalid; Supposed to have 5 Keys", diveTableCheck.surfaceIntervalTimeTable.get("E").size() == 5);
		assertTrue("Column E is invalid; Key is supposed to be 395, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("E").get(395).equals("A"));
		assertTrue("Column E is invalid; Key is supposed to be 205, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("E").get(205).equals("B"));
		assertTrue("Column E is invalid; Key is supposed to be 118, Value supposed to be C", diveTableCheck.surfaceIntervalTimeTable.get("E").get(118).equals("C"));
		assertTrue("Column E is invalid; Key is supposed to be 55, Value supposed to be D", diveTableCheck.surfaceIntervalTimeTable.get("E").get(55).equals("D"));
		assertTrue("Column E is invalid; Key is supposed to be 10, Value supposed to be E", diveTableCheck.surfaceIntervalTimeTable.get("E").get(10).equals("E"));
		
		assertTrue("Column F Size is invalid; Supposed to have 6 Keys", diveTableCheck.surfaceIntervalTimeTable.get("F").size() == 6);
		assertTrue("Column F is invalid; Key is supposed to be 426, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("F").get(426).equals("A"));
		assertTrue("Column F is invalid; Key is supposed to be 238, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("F").get(238).equals("B"));
		assertTrue("Column F is invalid; Key is supposed to be 149, Value supposed to be C", diveTableCheck.surfaceIntervalTimeTable.get("F").get(149).equals("C"));
		assertTrue("Column F is invalid; Key is supposed to be 90, Value supposed to be D", diveTableCheck.surfaceIntervalTimeTable.get("F").get(90).equals("D"));
		assertTrue("Column F is invalid; Key is supposed to be 46, Value supposed to be E", diveTableCheck.surfaceIntervalTimeTable.get("F").get(46).equals("E"));
		assertTrue("Column F is invalid; Key is supposed to be 10, Value supposed to be F", diveTableCheck.surfaceIntervalTimeTable.get("F").get(10).equals("F"));
		
		assertTrue("Column G Size is invalid; Supposed to have 7 Keys", diveTableCheck.surfaceIntervalTimeTable.get("G").size() == 7);
		assertTrue("Column G is invalid; Key is supposed to be 456, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("G").get(456).equals("A"));
		assertTrue("Column G is invalid; Key is supposed to be 266, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("G").get(266).equals("B"));
		assertTrue("Column G is invalid; Key is supposed to be 179, Value supposed to be C", diveTableCheck.surfaceIntervalTimeTable.get("G").get(179).equals("C"));
		assertTrue("Column G is invalid; Key is supposed to be 120, Value supposed to be D", diveTableCheck.surfaceIntervalTimeTable.get("G").get(120).equals("D"));
		assertTrue("Column G is invalid; Key is supposed to be 76, Value supposed to be E", diveTableCheck.surfaceIntervalTimeTable.get("G").get(76).equals("E"));
		assertTrue("Column G is invalid; Key is supposed to be 41, Value supposed to be F", diveTableCheck.surfaceIntervalTimeTable.get("G").get(41).equals("F"));
		assertTrue("Column G is invalid; Key is supposed to be 10, Value supposed to be G", diveTableCheck.surfaceIntervalTimeTable.get("G").get(10).equals("G"));
		
		assertTrue("Column H Size is invalid; Supposed to have 8 Keys", diveTableCheck.surfaceIntervalTimeTable.get("H").size() == 8);
		assertTrue("Column H is invalid; Key is supposed to be 480, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("H").get(480).equals("A"));
		assertTrue("Column H is invalid; Key is supposed to be 290, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("H").get(290).equals("B"));
		assertTrue("Column H is invalid; Key is supposed to be 201, Value supposed to be C", diveTableCheck.surfaceIntervalTimeTable.get("H").get(201).equals("C"));
		assertTrue("Column H is invalid; Key is supposed to be 144, Value supposed to be D", diveTableCheck.surfaceIntervalTimeTable.get("H").get(144).equals("D"));
		assertTrue("Column H is invalid; Key is supposed to be 102, Value supposed to be E", diveTableCheck.surfaceIntervalTimeTable.get("H").get(102).equals("E"));
		assertTrue("Column H is invalid; Key is supposed to be 67, Value supposed to be F", diveTableCheck.surfaceIntervalTimeTable.get("H").get(67).equals("F"));
		assertTrue("Column H is invalid; Key is supposed to be 37, Value supposed to be G", diveTableCheck.surfaceIntervalTimeTable.get("H").get(37).equals("G"));
		assertTrue("Column H is invalid; Key is supposed to be 10, Value supposed to be H", diveTableCheck.surfaceIntervalTimeTable.get("H").get(10).equals("H"));
		
		assertTrue("Column I Size is invalid; Supposed to have 9 Keys", diveTableCheck.surfaceIntervalTimeTable.get("I").size() == 9);
		assertTrue("Column I is invalid; Key is supposed to be 502, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("I").get(502).equals("A"));
		assertTrue("Column I is invalid; Key is supposed to be 313, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("I").get(313).equals("B"));
		assertTrue("Column I is invalid; Key is supposed to be 224, Value supposed to be C", diveTableCheck.surfaceIntervalTimeTable.get("I").get(224).equals("C"));
		assertTrue("Column I is invalid; Key is supposed to be 165, Value supposed to be D", diveTableCheck.surfaceIntervalTimeTable.get("I").get(165).equals("D"));
		assertTrue("Column I is invalid; Key is supposed to be 123, Value supposed to be E", diveTableCheck.surfaceIntervalTimeTable.get("I").get(123).equals("E"));
		assertTrue("Column I is invalid; Key is supposed to be 90, Value supposed to be F", diveTableCheck.surfaceIntervalTimeTable.get("I").get(90).equals("F"));
		assertTrue("Column I is invalid; Key is supposed to be 60, Value supposed to be G", diveTableCheck.surfaceIntervalTimeTable.get("I").get(60).equals("G"));
		assertTrue("Column I is invalid; Key is supposed to be 34, Value supposed to be H", diveTableCheck.surfaceIntervalTimeTable.get("I").get(34).equals("H"));
		assertTrue("Column I is invalid; Key is supposed to be 10, Value supposed to be I", diveTableCheck.surfaceIntervalTimeTable.get("I").get(10).equals("I"));
		
		assertTrue("Column J Size is invalid; Supposed to have 10 Keys", diveTableCheck.surfaceIntervalTimeTable.get("J").size() == 10);
		assertTrue("Column J is invalid; Key is supposed to be 531, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("J").get(531).equals("A"));
		assertTrue("Column J is invalid; Key is supposed to be 341, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("J").get(341).equals("B"));
		assertTrue("Column J is invalid; Key is supposed to be 243, Value supposed to be C", diveTableCheck.surfaceIntervalTimeTable.get("J").get(243).equals("C"));
		assertTrue("Column J is invalid; Key is supposed to be 185, Value supposed to be D", diveTableCheck.surfaceIntervalTimeTable.get("J").get(185).equals("D"));
		assertTrue("Column J is invalid; Key is supposed to be 141, Value supposed to be E", diveTableCheck.surfaceIntervalTimeTable.get("J").get(141).equals("E"));
		assertTrue("Column J is invalid; Key is supposed to be 108, Value supposed to be F", diveTableCheck.surfaceIntervalTimeTable.get("J").get(108).equals("F"));
		assertTrue("Column J is invalid; Key is supposed to be 80, Value supposed to be G", diveTableCheck.surfaceIntervalTimeTable.get("J").get(80).equals("G"));
		assertTrue("Column J is invalid; Key is supposed to be 55, Value supposed to be H", diveTableCheck.surfaceIntervalTimeTable.get("J").get(55).equals("H"));
		assertTrue("Column J is invalid; Key is supposed to be 32, Value supposed to be I", diveTableCheck.surfaceIntervalTimeTable.get("J").get(32).equals("I"));
		assertTrue("Column J is invalid; Key is supposed to be 10, Value supposed to be J", diveTableCheck.surfaceIntervalTimeTable.get("J").get(10).equals("J"));
		
		assertTrue("Column K Size is invalid; Supposed to have 11 Keys", diveTableCheck.surfaceIntervalTimeTable.get("K").size() == 11);
		assertTrue("Column K is invalid; Key is supposed to be 201, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("K").get(539).equals("A"));
		assertTrue("Column K is invalid; Key is supposed to be 341, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("K").get(349).equals("B"));
		assertTrue("Column K is invalid; Key is supposed to be 243, Value supposed to be C", diveTableCheck.surfaceIntervalTimeTable.get("K").get(260).equals("C"));
		assertTrue("Column K is invalid; Key is supposed to be 185, Value supposed to be D", diveTableCheck.surfaceIntervalTimeTable.get("K").get(202).equals("D"));
		assertTrue("Column K is invalid; Key is supposed to be 141, Value supposed to be E", diveTableCheck.surfaceIntervalTimeTable.get("K").get(159).equals("E"));
		assertTrue("Column K is invalid; Key is supposed to be 108, Value supposed to be F", diveTableCheck.surfaceIntervalTimeTable.get("K").get(124).equals("F"));
		assertTrue("Column K is invalid; Key is supposed to be 80, Value supposed to be G", diveTableCheck.surfaceIntervalTimeTable.get("K").get(96).equals("G"));
		assertTrue("Column K is invalid; Key is supposed to be 55, Value supposed to be H", diveTableCheck.surfaceIntervalTimeTable.get("K").get(72).equals("H"));
		assertTrue("Column K is invalid; Key is supposed to be 32, Value supposed to be I", diveTableCheck.surfaceIntervalTimeTable.get("K").get(50).equals("I"));
		assertTrue("Column K is invalid; Key is supposed to be 10, Value supposed to be J", diveTableCheck.surfaceIntervalTimeTable.get("K").get(29).equals("J"));
		assertTrue("Column K is invalid; Key is supposed to be 10, Value supposed to be K", diveTableCheck.surfaceIntervalTimeTable.get("K").get(10).equals("K"));
		
		assertTrue("Column L Size is invalid; Supposed to have 12 Keys", diveTableCheck.surfaceIntervalTimeTable.get("L").size() == 12);
		assertTrue("Column L is invalid; Key is supposed to be 201, Value supposed to be A", diveTableCheck.surfaceIntervalTimeTable.get("L").get(553).equals("A"));
		assertTrue("Column L is invalid; Key is supposed to be 341, Value supposed to be B", diveTableCheck.surfaceIntervalTimeTable.get("L").get(363).equals("B"));
		assertTrue("Column L is invalid; Key is supposed to be 243, Value supposed to be C", diveTableCheck.surfaceIntervalTimeTable.get("L").get(276).equals("C"));
		assertTrue("Column L is invalid; Key is supposed to be 185, Value supposed to be D", diveTableCheck.surfaceIntervalTimeTable.get("L").get(217).equals("D"));
		assertTrue("Column L is invalid; Key is supposed to be 141, Value supposed to be E", diveTableCheck.surfaceIntervalTimeTable.get("L").get(174).equals("E"));
		assertTrue("Column L is invalid; Key is supposed to be 108, Value supposed to be F", diveTableCheck.surfaceIntervalTimeTable.get("L").get(140).equals("F"));
		assertTrue("Column L is invalid; Key is supposed to be 80, Value supposed to be G", diveTableCheck.surfaceIntervalTimeTable.get("L").get(110).equals("G"));
		assertTrue("Column L is invalid; Key is supposed to be 55, Value supposed to be H", diveTableCheck.surfaceIntervalTimeTable.get("L").get(86).equals("H"));
		assertTrue("Column L is invalid; Key is supposed to be 32, Value supposed to be I", diveTableCheck.surfaceIntervalTimeTable.get("L").get(65).equals("I"));
		assertTrue("Column L is invalid; Key is supposed to be 10, Value supposed to be J", diveTableCheck.surfaceIntervalTimeTable.get("L").get(46).equals("J"));
		assertTrue("Column L is invalid; Key is supposed to be 10, Value supposed to be K", diveTableCheck.surfaceIntervalTimeTable.get("L").get(27).equals("K"));
		assertTrue("Column L is invalid; Key is supposed to be 10, Value supposed to be L", diveTableCheck.surfaceIntervalTimeTable.get("L").get(10).equals("L"));
	}
	
	@Test
	public void checkRDTtable() {
		System.out.println("\n\"Test 5: Checking repetitiveDiveTimeTable Data\"");
		DiveTable diveTableCheck = new DiveTable();
		/*
		 * Note: The following tests just shows if there are two values in the arrays for repetitiveDiveTimeTable. Values will be checked manually.
		 * */
		
		//Row A
		assertTrue("Row A is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("A").get(40).length == 2);
		assertTrue("Row A is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("A").get(50).length == 2);
		assertTrue("Row A is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("A").get(60).length == 2);
		assertTrue("Row A is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("A").get(70).length == 2);
		assertTrue("Row A is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("A").get(80).length == 2);
		assertTrue("Row A is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("A").get(90).length == 2);
		assertTrue("Row A is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("A").get(100).length == 2);
		assertTrue("Row A is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("A").get(110).length == 2);
		assertTrue("Row A is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("A").get(120).length == 2);
		assertTrue("Row A is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("A").get(130).length == 2);
		
		//Row B
		assertTrue("Row B is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("B").get(40).length == 2);
		assertTrue("Row B is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("B").get(50).length == 2);
		assertTrue("Row B is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("B").get(60).length == 2);
		assertTrue("Row B is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("B").get(70).length == 2);
		assertTrue("Row B is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("B").get(80).length == 2);
		assertTrue("Row B is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("B").get(90).length == 2);
		assertTrue("Row B is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("B").get(100).length == 2);
		assertTrue("Row B is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("B").get(110).length == 2);
		assertTrue("Row B is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("B").get(120).length == 2);
		assertTrue("Row B is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("B").get(130).length == 2);
		
		//Row C
		assertTrue("Row C is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("C").get(40).length == 2);
		assertTrue("Row C is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("C").get(50).length == 2);
		assertTrue("Row C is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("C").get(60).length == 2);
		assertTrue("Row C is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("C").get(70).length == 2);
		assertTrue("Row C is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("C").get(80).length == 2);
		assertTrue("Row C is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("C").get(90).length == 2);
		assertTrue("Row C is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("C").get(100).length == 2);
		assertTrue("Row C is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("C").get(110).length == 2);
		assertTrue("Row C is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("C").get(120).length == 2);
		assertTrue("Row C is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("C").get(130).length == 2);
		
		
		//Row D
		assertTrue("Row D is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("D").get(40).length == 2);
		assertTrue("Row D is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("D").get(50).length == 2);
		assertTrue("Row D is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("D").get(60).length == 2);
		assertTrue("Row D is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("D").get(70).length == 2);
		assertTrue("Row D is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("D").get(80).length == 2);
		assertTrue("Row D is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("D").get(90).length == 2);
		assertTrue("Row D is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("D").get(100).length == 2);
		assertTrue("Row D is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("D").get(110).length == 2);
		assertTrue("Row D is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("D").get(120).length == 2);
		assertTrue("Row D is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("D").get(130).length == 2);
		
		//Row E
		assertTrue("Row E is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("E").get(40).length == 2);
		assertTrue("Row E is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("E").get(50).length == 2);
		assertTrue("Row E is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("E").get(60).length == 2);
		assertTrue("Row E is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("E").get(70).length == 2);
		assertTrue("Row E is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("E").get(80).length == 2);
		assertTrue("Row E is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("E").get(90).length == 2);
		assertTrue("Row E is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("E").get(100).length == 2);
		assertTrue("Row E is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("E").get(110).length == 2);
		assertTrue("Row E is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("E").get(120).length == 2);
		assertTrue("Row E is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("E").get(130).length == 2);
		
		//Row F
		assertTrue("Row F is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("F").get(40).length == 2);
		assertTrue("Row F is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("F").get(50).length == 2);
		assertTrue("Row F is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("F").get(60).length == 2);
		assertTrue("Row F is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("F").get(70).length == 2);
		assertTrue("Row F is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("F").get(80).length == 2);
		assertTrue("Row F is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("F").get(90).length == 2);
		assertTrue("Row F is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("F").get(100).length == 2);
		assertTrue("Row F is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("F").get(110).length == 2);
		assertTrue("Row F is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("F").get(120).length == 2);
		assertTrue("Row F is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("F").get(130).length == 2);
		
		//Row G
		assertTrue("Row G is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("G").get(40).length == 2);
		assertTrue("Row G is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("G").get(50).length == 2);
		assertTrue("Row G is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("G").get(60).length == 2);
		assertTrue("Row G is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("G").get(70).length == 2);
		assertTrue("Row G is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("G").get(80).length == 2);
		assertTrue("Row G is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("G").get(90).length == 2);
		assertTrue("Row G is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("G").get(100).length == 2);
		assertTrue("Row G is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("G").get(110).length == 2);
		assertTrue("Row G is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("G").get(120).length == 2);
		assertTrue("Row G is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("G").get(130).length == 2);
		
		//Row H
		assertTrue("Row H is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("H").get(40).length == 2);
		assertTrue("Row H is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("H").get(50).length == 2);
		assertTrue("Row H is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("H").get(60).length == 2);
		assertTrue("Row H is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("H").get(70).length == 2);
		assertTrue("Row H is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("H").get(80).length == 2);
		assertTrue("Row H is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("H").get(90).length == 2);
		assertTrue("Row H is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("H").get(100).length == 2);
		assertTrue("Row H is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("H").get(110).length == 2);
		assertTrue("Row H is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("H").get(120).length == 2);
		assertTrue("Row H is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("H").get(130).length == 2);
		
		//Row I
		assertTrue("Row I is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("I").get(40).length == 2);
		assertTrue("Row I is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("I").get(50).length == 2);
		assertTrue("Row I is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("I").get(60).length == 2);
		assertTrue("Row I is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("I").get(70).length == 2);
		assertTrue("Row I is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("I").get(80).length == 2);
		assertTrue("Row I is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("I").get(90).length == 2);
		assertTrue("Row I is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("I").get(100).length == 2);
		assertTrue("Row I is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("I").get(110).length == 2);
		assertTrue("Row I is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("I").get(120).length == 2);
		assertTrue("Row I is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("I").get(130).length == 2);
		
		//Row J
		assertTrue("Row J is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("J").get(40).length == 2);
		assertTrue("Row J is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("J").get(50).length == 2);
		assertTrue("Row J is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("J").get(60).length == 2);
		assertTrue("Row J is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("J").get(70).length == 2);
		assertTrue("Row J is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("J").get(80).length == 2);
		assertTrue("Row J is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("J").get(90).length == 2);
		assertTrue("Row J is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("J").get(100).length == 2);
		assertTrue("Row J is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("J").get(110).length == 2);
		assertTrue("Row J is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("J").get(120).length == 2);
		assertTrue("Row J is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("J").get(130).length == 2);
		
		//Row K
		assertTrue("Row K is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("K").get(40).length == 2);
		assertTrue("Row K is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("K").get(50).length == 2);
		assertTrue("Row K is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("K").get(60).length == 2);
		assertTrue("Row K is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("K").get(70).length == 2);
		assertTrue("Row K is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("K").get(80).length == 2);
		assertTrue("Row K is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("K").get(90).length == 2);
		assertTrue("Row K is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("K").get(100).length == 2);
		assertTrue("Row K is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("K").get(110).length == 2);
		assertTrue("Row K is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("K").get(120).length == 2);
		assertTrue("Row K is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("K").get(130).length == 2);
		
		//Row L
		assertTrue("Row L is invalid; Key is supposed to be 40 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("L").get(40).length == 2);
		assertTrue("Row L is invalid; Key is supposed to be 50 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("L").get(50).length == 2);
		assertTrue("Row L is invalid; Key is supposed to be 60 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("L").get(60).length == 2);
		assertTrue("Row L is invalid; Key is supposed to be 70 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("L").get(70).length == 2);
		assertTrue("Row L is invalid; Key is supposed to be 80 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("L").get(80).length == 2);
		assertTrue("Row L is invalid; Key is supposed to be 90 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("L").get(90).length == 2);
		assertTrue("Row L is invalid; Key is supposed to be 100 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("L").get(100).length == 2);
		assertTrue("Row L is invalid; Key is supposed to be 110 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("L").get(110).length == 2);
		assertTrue("Row L is invalid; Key is supposed to be 120 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("L").get(120).length == 2);
		assertTrue("Row L is invalid; Key is supposed to be 130 and have two values", diveTableCheck.repetitiveDiveTimeTable.get("L").get(130).length == 2);
		
		/*if (diveTableCheck.repetitiveDiveTimeTable.get("A").get(40).length == 2) {
			Set set = diveTableCheck.repetitiveDiveTimeTable.get("A").entrySet();
			Iterator i = set.iterator();
			while (i.hasNext()) {
				Map.Entry entry = (Entry)i.next();
				System.out.println(entry.getKey() + ", " + entry.getValue());
			}
		}*/
			
		//System.out.println(diveTableCheck.repetitiveDiveTimeTable.get("A").get(40));
		/*System.out.println(diveTableCheck.repetitiveDiveTimeTable.get("A").get(40).length);
		for (int i = 0; i < diveTableCheck.repetitiveDiveTimeTable.get("A").get(40).length; i++) {
			System.out.println(diveTableCheck.repetitiveDiveTimeTableLookup("A", 40, 7));
		}*/
		
		
		
	}
	
}
