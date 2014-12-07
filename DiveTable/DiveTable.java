import javafx.scene.control.Tab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * DiveTable class
 * @author David A.
 */
public class DiveTable {

    // Table 1: Mapping depth (in ft.) to another map of dive time minutes mapped to pressure group.
    Map<Integer, Map<Integer, TableOneCellOutput>> endOfDiveTable = new LinkedHashMap<Integer, Map<Integer, TableOneCellOutput>>();

    // Table 2: Mapping Pressure Group to another map of surface interval minutes mapped to pressure group.
    Map<String, Map<Integer, String>> surfaceIntervalTimeTable = new LinkedHashMap<String, Map<Integer, String>>();

    // Table 3: Mapping Pressure Group to another map of depth mapped to an array of Residual Nitrogen Times and Max Dive Times.
    Map<String, Map<Integer, Integer[]>> repetitiveDiveTimeTable = new LinkedHashMap<String, Map<Integer, Integer[]>>();

    Map<Integer, SingleDiveData> diveResultsMap = new LinkedHashMap<Integer, SingleDiveData>();

    //If 0, no errors with input dive data. If > 0, the number indicates which dive# the FIRST error occurred at.
    //Can then use this number on diveResultsMap to grab the actual error.
    int errorFound = 0;

    public static void main(String[] args) {

        //Tests
        //Legal Dive
        System.out.println("Legal dive test");
        List<Integer> testDives = new ArrayList<Integer>();
        testDives.add(80); //Depth
        testDives.add(41); //Time
        testDives.add(118); //SIT

        testDives.add(20); //Depth
        testDives.add(21); //Time
        testDives.add(22); //SIT

        testDives.add(10);
        testDives.add(11);
        testDives.add(12);

        DiveTable dt = new DiveTable();
        dt.calculateDiveTableResults(testDives);
        System.out.println(dt);


        //Illegal Dive
        System.out.println("\n\nIllegal dive test");
        List<Integer> testDives2 = new ArrayList<Integer>();
        testDives2.add(50);
        testDives2.add(55);
        testDives2.add(110);
        testDives2.add(50);
        testDives2.add(51);
        testDives2.add(52);
        testDives2.add(10);
        testDives2.add(11);
        testDives2.add(12);

        DiveTable dt2 = new DiveTable();
        dt2.calculateDiveTableResults(testDives2);
        System.out.println(dt2);
    }


    /**
     * Constructor that populates all three NAUI dive tables.
     */
    public DiveTable() {
        // Populate Table 1: endOfDiveTable
        // Row 40
        Map<Integer, TableOneCellOutput> row40Map = new LinkedHashMap<Integer, TableOneCellOutput>(); //Mapping time (in mins.) to Pressure Group
        row40Map.put(5, new TableOneCellOutput("A"));
        row40Map.put(15, new TableOneCellOutput("B"));
        row40Map.put(25, new TableOneCellOutput("C"));
        row40Map.put(30, new TableOneCellOutput("D"));
        row40Map.put(40, new TableOneCellOutput("E"));
        row40Map.put(50, new TableOneCellOutput("F"));
        row40Map.put(70, new TableOneCellOutput("G"));
        row40Map.put(80, new TableOneCellOutput("H"));
        row40Map.put(100, new TableOneCellOutput("I"));
        row40Map.put(110, new TableOneCellOutput("J"));
        row40Map.put(130, new TableOneCellOutput("K"));
        row40Map.put(150, new TableOneCellOutput("L", 5));

        endOfDiveTable.put(40, row40Map);

        // Row 50
        Map<Integer, TableOneCellOutput> row50Map = new LinkedHashMap<Integer, TableOneCellOutput>();
        row50Map.put(10, new TableOneCellOutput("B"));
        row50Map.put(15, new TableOneCellOutput("C"));
        row50Map.put(25, new TableOneCellOutput("D"));
        row50Map.put(30, new TableOneCellOutput("E"));
        row50Map.put(40, new TableOneCellOutput("F"));
        row50Map.put(50, new TableOneCellOutput("G"));
        row50Map.put(60, new TableOneCellOutput("H"));
        row50Map.put(70, new TableOneCellOutput("I"));
        row50Map.put(80, new TableOneCellOutput("J"));
        row50Map.put(100, new TableOneCellOutput("L", 5));

        endOfDiveTable.put(50, row50Map);

        // Row 60
        Map<Integer, TableOneCellOutput> row60Map = new LinkedHashMap<Integer, TableOneCellOutput>();
        row60Map.put(10, new TableOneCellOutput("B"));
        row60Map.put(15, new TableOneCellOutput("C"));
        row60Map.put(20, new TableOneCellOutput("D"));
        row60Map.put(25, new TableOneCellOutput("E"));
        row60Map.put(30, new TableOneCellOutput("F"));
        row60Map.put(40, new TableOneCellOutput("G"));
        row60Map.put(50, new TableOneCellOutput("H"));
        row60Map.put(55, new TableOneCellOutput("I"));
        row60Map.put(60, new TableOneCellOutput("J", 5));
        row60Map.put(80, new TableOneCellOutput("L", 7));

        endOfDiveTable.put(60, row60Map);

        // Row 70
        Map<Integer, TableOneCellOutput> row70Map = new LinkedHashMap<Integer, TableOneCellOutput>();
        row70Map.put(5, new TableOneCellOutput("B"));
        row70Map.put(10, new TableOneCellOutput("C"));
        row70Map.put(15, new TableOneCellOutput("D"));
        row70Map.put(20, new TableOneCellOutput("E"));
        row70Map.put(30, new TableOneCellOutput("F"));
        row70Map.put(35, new TableOneCellOutput("G"));
        row70Map.put(40, new TableOneCellOutput("H"));
        row70Map.put(45, new TableOneCellOutput("I"));
        row70Map.put(50, new TableOneCellOutput("J", 5));
        row70Map.put(60, new TableOneCellOutput("K", 8));
        row70Map.put(70, new TableOneCellOutput("L", 14));

        endOfDiveTable.put(70, row70Map);

        // Row 80
        Map<Integer, TableOneCellOutput> row80Map = new LinkedHashMap<Integer, TableOneCellOutput>();
        row80Map.put(5, new TableOneCellOutput("B"));
        row80Map.put(10, new TableOneCellOutput("C"));
        row80Map.put(15, new TableOneCellOutput("D"));
        row80Map.put(20, new TableOneCellOutput("E"));
        row80Map.put(25, new TableOneCellOutput("F"));
        row80Map.put(30, new TableOneCellOutput("G"));
        row80Map.put(35, new TableOneCellOutput("H"));
        row80Map.put(40, new TableOneCellOutput("I", 5));
        row80Map.put(50, new TableOneCellOutput("K", 10));
        row80Map.put(60, new TableOneCellOutput("L", 17));

        endOfDiveTable.put(80, row80Map);

        // Row 90
        Map<Integer, TableOneCellOutput> row90Map = new LinkedHashMap<Integer, TableOneCellOutput>();
        row90Map.put(5, new TableOneCellOutput("B"));
        row90Map.put(10, new TableOneCellOutput("C"));
        row90Map.put(12, new TableOneCellOutput("D"));
        row90Map.put(15, new TableOneCellOutput("E"));
        row90Map.put(20, new TableOneCellOutput("F"));
        row90Map.put(25, new TableOneCellOutput("G"));
        row90Map.put(30, new TableOneCellOutput("H", 5));
        row90Map.put(40, new TableOneCellOutput("J", 7));
        row90Map.put(50, new TableOneCellOutput("L", 18));

        endOfDiveTable.put(90, row90Map);

        // Row 100
        Map<Integer, TableOneCellOutput> row100Map = new LinkedHashMap<Integer, TableOneCellOutput>();
        row100Map.put(5, new TableOneCellOutput("B"));
        row100Map.put(7, new TableOneCellOutput("C"));
        row100Map.put(10, new TableOneCellOutput("D"));
        row100Map.put(15, new TableOneCellOutput("E"));
        row100Map.put(20, new TableOneCellOutput("F"));
        row100Map.put(22, new TableOneCellOutput("G"));
        row100Map.put(25, new TableOneCellOutput("H", 5));
        row100Map.put(40, new TableOneCellOutput("K", 15));

        endOfDiveTable.put(100, row100Map);

        // Row 110
        Map<Integer, TableOneCellOutput> row110Map = new LinkedHashMap<Integer, TableOneCellOutput>();
        row110Map.put(5, new TableOneCellOutput("C"));
        row110Map.put(10, new TableOneCellOutput("D"));
        row110Map.put(13, new TableOneCellOutput("E"));
        row110Map.put(15, new TableOneCellOutput("F"));
        row110Map.put(20, new TableOneCellOutput("G", 5));
        row110Map.put(30, new TableOneCellOutput("J", 7));

        endOfDiveTable.put(110, row110Map);

        // Row 120
        Map<Integer, TableOneCellOutput> row120Map = new LinkedHashMap<Integer, TableOneCellOutput>();
        row120Map.put(5, new TableOneCellOutput("C"));
        row120Map.put(10, new TableOneCellOutput("D"));
        row120Map.put(12, new TableOneCellOutput("E"));
        row120Map.put(15, new TableOneCellOutput("F", 5));
        row120Map.put(25, new TableOneCellOutput("I", 6));
        row120Map.put(30, new TableOneCellOutput("J", 14));

        endOfDiveTable.put(120, row120Map);

        // Row 130
        Map<Integer, TableOneCellOutput> row130Map = new LinkedHashMap<Integer, TableOneCellOutput>();
        row130Map.put(5, new TableOneCellOutput("C"));
        row130Map.put(8, new TableOneCellOutput("D"));
        row130Map.put(10, new TableOneCellOutput("E", 5));
        row130Map.put(25, new TableOneCellOutput("J", 10));

        endOfDiveTable.put(130, row130Map);



        // Populate Table 2: surfaceIntervalTimeTable
        // Column A
        Map<Integer, String> columnAMap = new LinkedHashMap<Integer, String>();
        columnAMap.put(10, "A");

        surfaceIntervalTimeTable.put("A", columnAMap);

        // Column B
        Map<Integer, String> columnBMap = new LinkedHashMap<Integer, String>();
        columnBMap.put(201, "A");
        columnBMap.put(10, "B");

        surfaceIntervalTimeTable.put("B", columnBMap);

        // Column C
        Map<Integer, String> columnCMap = new LinkedHashMap<Integer, String>();
        columnCMap.put(290, "A");
        columnCMap.put(100, "B");
        columnCMap.put(10, "C");

        surfaceIntervalTimeTable.put("C", columnCMap);

        // Column D
        Map<Integer, String> columnDMap = new LinkedHashMap<Integer, String>();
        columnDMap.put(349, "A");
        columnDMap.put(159, "B");
        columnDMap.put(70, "C");
        columnDMap.put(10, "D");

        surfaceIntervalTimeTable.put("D", columnDMap);

        // Column E
        Map<Integer, String> columnEMap = new LinkedHashMap<Integer, String>();
        columnEMap.put(395, "A");
        columnEMap.put(205, "B");
        columnEMap.put(118, "C");
        columnEMap.put(55, "D");
        columnEMap.put(10, "E");

        surfaceIntervalTimeTable.put("E", columnEMap);

        // Column F
        Map<Integer, String> columnFMap = new LinkedHashMap<Integer, String>();
        columnFMap.put(426, "A");
        columnFMap.put(238, "B");
        columnFMap.put(149, "C");
        columnFMap.put(90, "D");
        columnFMap.put(46, "E");
        columnFMap.put(10, "F");

        surfaceIntervalTimeTable.put("F", columnFMap);

        // Column G
        Map<Integer, String> columnGMap = new LinkedHashMap<Integer, String>();
        columnGMap.put(456, "A");
        columnGMap.put(266, "B");
        columnGMap.put(179, "C");
        columnGMap.put(120, "D");
        columnGMap.put(76, "E");
        columnGMap.put(41, "F");
        columnGMap.put(10, "G");

        surfaceIntervalTimeTable.put("G", columnGMap);

        // Column H
        Map<Integer, String> columnHMap = new LinkedHashMap<Integer, String>();
        columnHMap.put(480, "A");
        columnHMap.put(290, "B");
        columnHMap.put(201, "C");
        columnHMap.put(144, "D");
        columnHMap.put(102, "E");
        columnHMap.put(67, "F");
        columnHMap.put(37, "G");
        columnHMap.put(10, "H");

        surfaceIntervalTimeTable.put("H", columnHMap);

        // Column I
        Map<Integer, String> columnIMap = new LinkedHashMap<Integer, String>();
        columnIMap.put(502, "A");
        columnIMap.put(313, "B");
        columnIMap.put(224, "C");
        columnIMap.put(165, "D");
        columnIMap.put(123, "E");
        columnIMap.put(90, "F");
        columnIMap.put(60, "G");
        columnIMap.put(34, "H");
        columnIMap.put(10, "I");

        surfaceIntervalTimeTable.put("I", columnIMap);

        // Column J
        Map<Integer, String> columnJMap = new LinkedHashMap<Integer, String>();
        columnJMap.put(531, "A");
        columnJMap.put(341, "B");
        columnJMap.put(243, "C");
        columnJMap.put(185, "D");
        columnJMap.put(141, "E");
        columnJMap.put(108, "F");
        columnJMap.put(80, "G");
        columnJMap.put(55, "H");
        columnJMap.put(32, "I");
        columnJMap.put(10, "J");

        surfaceIntervalTimeTable.put("J", columnJMap);

        // Column K
        Map<Integer, String> columnKMap = new LinkedHashMap<Integer, String>();
        columnKMap.put(539, "A");
        columnKMap.put(349, "B");
        columnKMap.put(260, "C");
        columnKMap.put(202, "D");
        columnKMap.put(159, "E");
        columnKMap.put(124, "F");
        columnKMap.put(96, "G");
        columnKMap.put(72, "H");
        columnKMap.put(50, "I");
        columnKMap.put(29, "J");
        columnKMap.put(10, "K");

        surfaceIntervalTimeTable.put("K", columnKMap);

        // Column L
        Map<Integer, String> columnLMap = new LinkedHashMap<Integer, String>();
        columnLMap.put(553, "A");
        columnLMap.put(363, "B");
        columnLMap.put(276, "C");
        columnLMap.put(217, "D");
        columnLMap.put(174, "E");
        columnLMap.put(140, "F");
        columnLMap.put(110, "G");
        columnLMap.put(86, "H");
        columnLMap.put(65, "I");
        columnLMap.put(46, "J");
        columnLMap.put(27, "K");
        columnLMap.put(10, "L");

        surfaceIntervalTimeTable.put("L", columnLMap);


        //Populate Table 3: repetitiveDiveTimeTable
        //Note: I am entering rows in the opposite direction (left to right) as displayed on official NAUI table 3.

        // Row A
        Map<Integer, Integer[]> rowAMap = new LinkedHashMap<Integer, Integer[]>();
        rowAMap.put(40, new Integer[]{7, 123});
        rowAMap.put(50, new Integer[]{6, 74});
        rowAMap.put(60, new Integer[]{5, 50});
        rowAMap.put(70, new Integer[]{4, 41});
        rowAMap.put(80, new Integer[]{4, 31});
        rowAMap.put(90, new Integer[]{3, 22});
        rowAMap.put(100, new Integer[]{3, 19});
        rowAMap.put(110, new Integer[]{3, 12});
        rowAMap.put(120, new Integer[]{3, 9});
        rowAMap.put(130, new Integer[]{3, 5});

        repetitiveDiveTimeTable.put("A", rowAMap);

        // Row B
        Map<Integer, Integer[]> rowBMap = new LinkedHashMap<Integer, Integer[]>();
        rowBMap.put(40, new Integer[]{17, 113});
        rowBMap.put(50, new Integer[]{13, 67});
        rowBMap.put(60, new Integer[]{11, 44});
        rowBMap.put(70, new Integer[]{9, 36});
        rowBMap.put(80, new Integer[]{8, 27});
        rowBMap.put(90, new Integer[]{7, 18});
        rowBMap.put(100, new Integer[]{7, 15});
        rowBMap.put(110, new Integer[]{6, 9});
        rowBMap.put(120, new Integer[]{6, 6});
        rowBMap.put(130, new Integer[]{6, 0});

        repetitiveDiveTimeTable.put("B", rowBMap);

        // Row C
        Map<Integer, Integer[]> rowCMap = new LinkedHashMap<Integer, Integer[]>();
        rowCMap.put(40, new Integer[]{25, 105});
        rowCMap.put(50, new Integer[]{21, 59});
        rowCMap.put(60, new Integer[]{17, 38});
        rowCMap.put(70, new Integer[]{15, 30});
        rowCMap.put(80, new Integer[]{13, 22});
        rowCMap.put(90, new Integer[]{11, 14});
        rowCMap.put(100, new Integer[]{10, 12});
        rowCMap.put(110, new Integer[]{10, 5});
        rowCMap.put(120, new Integer[]{9, 0});
        rowCMap.put(130, new Integer[]{8, 0});

        repetitiveDiveTimeTable.put("C", rowCMap);

        // Row D
        Map<Integer, Integer[]> rowDMap = new LinkedHashMap<Integer, Integer[]>();
        rowDMap.put(40, new Integer[]{37, 93});
        rowDMap.put(50, new Integer[]{29, 51});
        rowDMap.put(60, new Integer[]{24, 31});
        rowDMap.put(70, new Integer[]{20, 25});
        rowDMap.put(80, new Integer[]{18, 17});
        rowDMap.put(90, new Integer[]{16, 9});
        rowDMap.put(100, new Integer[]{14, 8});
        rowDMap.put(110, new Integer[]{13, 0});
        rowDMap.put(120, new Integer[]{12, 0});
        rowDMap.put(130, new Integer[]{11, 0});

        repetitiveDiveTimeTable.put("D", rowDMap);

        // Row E
        Map<Integer, Integer[]> rowEMap = new LinkedHashMap<Integer, Integer[]>();
        rowEMap.put(40, new Integer[]{49, 81});
        rowEMap.put(50, new Integer[]{38, 42});
        rowEMap.put(60, new Integer[]{30, 25});
        rowEMap.put(70, new Integer[]{26, 19});
        rowEMap.put(80, new Integer[]{23, 12});
        rowEMap.put(90, new Integer[]{20, 5});
        rowEMap.put(100, new Integer[]{18, 4});
        rowEMap.put(110, new Integer[]{16, 0});
        rowEMap.put(120, new Integer[]{15, 0});
        rowEMap.put(130, new Integer[]{13, 0});

        repetitiveDiveTimeTable.put("E", rowEMap);

        // Row F
        Map<Integer, Integer[]> rowFMap = new LinkedHashMap<Integer, Integer[]>();
        rowFMap.put(40, new Integer[]{61, 69});
        rowFMap.put(50, new Integer[]{47, 33});
        rowFMap.put(60, new Integer[]{36, 19});
        rowFMap.put(70, new Integer[]{31, 14});
        rowFMap.put(80, new Integer[]{28, 7});
        rowFMap.put(90, new Integer[]{24, 0});
        rowFMap.put(100, new Integer[]{22, 0});
        rowFMap.put(110, new Integer[]{20, 0});
        rowFMap.put(120, new Integer[]{18, 0});
        rowFMap.put(130, new Integer[]{16, 0});

        repetitiveDiveTimeTable.put("F", rowFMap);

        // Row G
        Map<Integer, Integer[]> rowGMap = new LinkedHashMap<Integer, Integer[]>();
        rowGMap.put(40, new Integer[]{73, 57});
        rowGMap.put(50, new Integer[]{56, 24});
        rowGMap.put(60, new Integer[]{44, 11});
        rowGMap.put(70, new Integer[]{37, 8});
        rowGMap.put(80, new Integer[]{32, 0});
        rowGMap.put(90, new Integer[]{29, 0});
        rowGMap.put(100, new Integer[]{26, 0});
        rowGMap.put(110, new Integer[]{24, 0});
        rowGMap.put(120, new Integer[]{21, 0});
        rowGMap.put(130, new Integer[]{19, 0});

        repetitiveDiveTimeTable.put("G", rowGMap);

        // Row H
        Map<Integer, Integer[]> rowHMap = new LinkedHashMap<Integer, Integer[]>();
        rowHMap.put(40, new Integer[]{87, 43});
        rowHMap.put(50, new Integer[]{66, 14});
        rowHMap.put(60, new Integer[]{52, 0});
        rowHMap.put(70, new Integer[]{43, 0});
        rowHMap.put(80, new Integer[]{38, 0});
        rowHMap.put(90, new Integer[]{33, 0});
        rowHMap.put(100, new Integer[]{30, 0});
        rowHMap.put(110, new Integer[]{27, 0});
        rowHMap.put(120, new Integer[]{25, 0});
        rowHMap.put(130, new Integer[]{22, 0});

        repetitiveDiveTimeTable.put("H", rowHMap);

        // Row I
        Map<Integer, Integer[]> rowIMap = new LinkedHashMap<Integer, Integer[]>();
        rowIMap.put(40, new Integer[]{101, 29});
        rowIMap.put(50, new Integer[]{76, 4});
        rowIMap.put(60, new Integer[]{61, 0});
        rowIMap.put(70, new Integer[]{50, 0});
        rowIMap.put(80, new Integer[]{43, 0});
        rowIMap.put(90, new Integer[]{38, 0});
        rowIMap.put(100, new Integer[]{34, 0});
        rowIMap.put(110, new Integer[]{31, 0});
        rowIMap.put(120, new Integer[]{28, 0});
        rowIMap.put(130, new Integer[]{25, 0});

        repetitiveDiveTimeTable.put("I", rowIMap);

        // Row J
        Map<Integer, Integer[]> rowJMap = new LinkedHashMap<Integer, Integer[]>();
        rowJMap.put(40, new Integer[]{116, 14});
        rowJMap.put(50, new Integer[]{87, 0}); //Changed second value from 4 to 0; 0 is the correct number
        rowJMap.put(60, new Integer[]{70, 0});
        rowJMap.put(70, new Integer[]{57, 0});
        rowJMap.put(80, new Integer[]{48, 0});
        rowJMap.put(90, new Integer[]{43, 0});
        rowJMap.put(100, new Integer[]{38, 0});
        rowJMap.put(110, new Integer[]{null, null}); //Avoid Repetitive Dive over 100 ft.
        rowJMap.put(120, new Integer[]{null, null}); //Avoid
        rowJMap.put(130, new Integer[]{null, null}); //Avoid

        repetitiveDiveTimeTable.put("J", rowJMap);

        // Row K
        Map<Integer, Integer[]> rowKMap = new LinkedHashMap<Integer, Integer[]>();
        rowKMap.put(40, new Integer[]{138, 0});
        rowKMap.put(50, new Integer[]{99, 0});
        rowKMap.put(60, new Integer[]{79, 0});
        rowKMap.put(70, new Integer[]{64, 0});
        rowKMap.put(80, new Integer[]{54, 0});
        rowKMap.put(90, new Integer[]{47, 0});
        rowKMap.put(100, new Integer[]{null, null});
        rowKMap.put(110, new Integer[]{null, null}); //Avoid Repetitive Dive over 100 ft.
        rowKMap.put(120, new Integer[]{null, null}); //Avoid
        rowKMap.put(130, new Integer[]{null, null}); //Avoid

        repetitiveDiveTimeTable.put("K", rowKMap);

        // Row L
        Map<Integer, Integer[]> rowLMap = new LinkedHashMap<Integer, Integer[]>();
        rowLMap.put(40, new Integer[]{161, 0});
        rowLMap.put(50, new Integer[]{111, 0});
        rowLMap.put(60, new Integer[]{88, 0});
        rowLMap.put(70, new Integer[]{72, 0});
        rowLMap.put(80, new Integer[]{61, 0});
        rowLMap.put(90, new Integer[]{53, 0});
        rowLMap.put(100, new Integer[]{null, null});
        rowLMap.put(110, new Integer[]{null, null}); //Avoid Repetitive Dive over 100 ft.
        rowLMap.put(120, new Integer[]{null, null}); //Avoid
        rowLMap.put(130, new Integer[]{null, null}); //Avoid

        repetitiveDiveTimeTable.put("L", rowLMap);

    }

    /**
     * Takes an ArrayList containing the user's dive information (dive depth, dive time, surface interval time),
     * and outputs data required to generate a proper dive profile.
     *
     * @param diveInfo The user's dive data.
     */
    public void calculateDiveTableResults (List<Integer> diveInfo) {
        String currentPressureGroup = ""; //Changes as we continuously run through loop.

        for (int i = 0; i < diveInfo.size(); i += 3) { //Note: Incrementing by 3, not 1.

            Integer diveNumber = ((i / 3) + 1); //Every 3rd element in list indicates a new dive.
            Integer diveDepth = diveInfo.get(i); //Depth is indicated first.
            Integer diveTime = diveInfo.get(i + 1); //Then DiveTime.
            Integer surfaceIntervalTime = diveInfo.get(i + 2); //Then Surface Interval Time.

            //Will hold all input data and final calculated data for each dive.
            SingleDiveData diveData = new SingleDiveData(diveDepth, diveTime, surfaceIntervalTime);

//            System.out.println("Dive #" + diveNumber + ", Depth: " + diveDepth + ", Time: " + diveTime + ", Surface Interval Time: " + surfaceIntervalTime);

            //For all non-initial dives, we look at repetitive dive table 3 and make adjustments to user input.
            if (i != 0) {
                //Table 3 Lookup
                Integer[] tableThreeResult = repetitiveDiveTimeTableLookup(currentPressureGroup, diveDepth, diveTime);

                //Check if diveTime is greater than Adjusted Maximum Dive Time. If it is, must report error.
                if(diveTime > tableThreeResult[1]) {
                    this.errorFound = diveNumber; //Set error location
                    diveData.errorMessage = "Error at Dive #" + diveNumber + ": Dive Time is greater than allowed Adjusted Maximum Dive Time.";
                }

                diveTime = diveInfo.get(i + 1) + tableThreeResult[0]; //TotalNitrogenTime = ActualDiveTime + Residual Nitrogen Time.
                diveData.totalNitrogenTime = diveTime;
//                System.out.println("\tTotal Nitrogen Time: " + diveTime);
            }

            //Table 1 Lookup
            TableOneCellOutput tableOneResult = tableOneLookup(diveDepth, diveTime);

            currentPressureGroup = tableOneResult.pressureGroup; //Currently indicates End-Of-Dive PG.
            diveData.endOfDivePG = currentPressureGroup;

            Integer decompressionTime = tableOneResult.decompressionTime;
            diveData.decompressionTime = decompressionTime;

//            System.out.println("\tEnd-Of-Dive Pressure Group: " + currentPressureGroup);
//            if (decompressionTime != 0) {
//                System.out.println("\tDecompression Time Required: " + decompressionTime);
//            } else {
//                System.out.println("\tDecompression Time Required: None");
//            }

            //Table 2 Lookup
            //Now currentPressureGroup will hold the new Pressure Group post-table2.
            currentPressureGroup = surfaceIntervalTimeLookup(currentPressureGroup, surfaceIntervalTime);
            diveData.postSurfaceIntervalPG = currentPressureGroup;
//            System.out.println("\tPost-Surface-Interval-Time Pressure Group: " + currentPressureGroup);

            //Add diveData to diveResultsMap
            this.diveResultsMap.put(diveNumber, diveData);

            //If error has been found during calculations, exit method prematurely.
            if (this.errorFound > 0) {
                return;
            }
        }
    }

    public TableOneCellOutput tableOneLookup(Integer diveDepth, Integer diveTime) {
        for (Map.Entry<Integer, Map<Integer, TableOneCellOutput>> entry : endOfDiveTable.entrySet()) {
            if (entry.getKey() >= diveDepth) { //Note: We round up to the next depth key for safety reasons.
                //Found key. Now iterate through its nested map.
                Map<Integer, TableOneCellOutput> nestedMap = entry.getValue();
                for (Map.Entry<Integer, TableOneCellOutput> nestedEntry : nestedMap.entrySet()) {
                    if (nestedEntry.getKey() >= diveTime) { //Rounding up to next time key for safety reasons.
                        //Found our target cell. Return it.
                        TableOneCellOutput resultCell = nestedEntry.getValue();

                        return resultCell;
                    }
                }
            }
        }

        return null;
    }

    public String surfaceIntervalTimeLookup(String pressureGroup, Integer surfaceIntervalTime) {
        //We have a previously calculated pressure group at this point, so we use it to grab the appropriate
        //column in the S.I.T. table.
        Map<Integer, String> columnMap = surfaceIntervalTimeTable.get(pressureGroup);

        //Now we iterate through map for given surface interval time.
        //Note: Though the officialNAUI table 2 contains a range of times in each cell, our map only holds the
        //lower bound time of each cell. This is the time that we will be comparing to.
        for (Map.Entry<Integer, String> entry : columnMap.entrySet()) {
            if (entry.getKey() <= surfaceIntervalTime) {
                //Found target cell. Return new Pressure Group letter.
                String newPressureGroup = entry.getValue();

                return newPressureGroup;
            }
        }

        return null;
    }

    public Integer[] repetitiveDiveTimeTableLookup(String pressureGroup, Integer diveDepth, Integer diveTime) {
        //Use previously calculated Pressure Group (from table 2 output) to grab relevant row mapping in Table 3.
        Map<Integer, Integer[]> rowMap = repetitiveDiveTimeTable.get(pressureGroup);

        //Now we iterate through map for given depth.
        //Note: rowMap is a mapping of dive depth to a size 2 integer array containing:
        //array[0]: Residual Nitrogen Time
        //array[1]: Adjusted Max Dive Time
        for (Map.Entry<Integer, Integer[]> entry : rowMap.entrySet()) {
            if (entry.getKey() >= diveDepth) { //Note: Rounding up to next nearest depth on the table.
                //Found target cell. Return new Pressure Group letter.
                Integer[] targetCell = entry.getValue();

                return targetCell;
            }
        }

        return null;
    }

    public String toString() {
        String divesInfo = ""; //Will return at end.

        if (this.errorFound > 0) {
            divesInfo += "*WARNING* Error Found at Dive #" + this.errorFound + ": " + this.diveResultsMap.get(this.errorFound).errorMessage + "\n";
        }

        for (Map.Entry<Integer, SingleDiveData> entry : this.diveResultsMap.entrySet()) {
            Integer diveNumber = entry.getKey();
            SingleDiveData diveData = entry.getValue();

            divesInfo += "\nDive #" + diveNumber + ", Depth: " + diveData.diveDepth + ", Time: " + diveData.diveTime + ", Surface Interval Time: " + diveData.surfaceIntervalTime;

            //Show TNT for all non-initial dives.
            if (diveNumber > 1) {
                divesInfo += "\n\tTotal Nitrogen Time: " + diveData.totalNitrogenTime;
            }

            divesInfo += "\n\tEnd-Of-Dive Pressure Group: " + diveData.endOfDivePG;
            divesInfo += "\n\tDecompression Time Required: " + diveData.decompressionTime;
            divesInfo += "\n\tPost-Surface-Interval-Time Pressure Group: " + diveData.postSurfaceIntervalPG;

            if (diveData.errorMessage == null) {
                divesInfo += "\n\tErrors: None";
            } else {
                divesInfo += "\n\tError Found: " + diveData.errorMessage;
            }
        }

        return divesInfo;
    }
}


/**
 * Class to hold Table 1 cell data.
 */
class TableOneCellOutput {
    String pressureGroup;
    Integer decompressionTime;

    public TableOneCellOutput(String PG) {
        this.pressureGroup = PG;
        this.decompressionTime = 0;
    }

    public TableOneCellOutput(String PG, Integer DT) {
        this.pressureGroup = PG;
        this.decompressionTime = DT;
    }
}

/**
 * Class to hold all data of a single dive, including calculated results.
 */
class SingleDiveData {
    Integer diveDepth; //From user input
    Integer diveTime; //From user input
    Integer surfaceIntervalTime; //From user input

    Integer totalNitrogenTime;
    String endOfDivePG;
    Integer decompressionTime;
    String postSurfaceIntervalPG;
    String errorMessage; //Will state the FIRST found error.

    public SingleDiveData (Integer depth, Integer time, Integer SIT) {
        this.diveDepth = depth;
        this.diveTime = time;
        this.surfaceIntervalTime = SIT;
        this.totalNitrogenTime = 0;
        this.decompressionTime = 0;
        this.errorMessage = null;
    }
}