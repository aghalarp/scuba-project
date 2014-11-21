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

    List<List<Integer>> endOfDiveTable1 = new ArrayList<List<Integer>>();

    // Table 1: Mapping depth (in ft.) to another map of dive time minutes mapped to pressure group.
    Map<Integer, Map<Integer, TableOneCellOutput>> endOfDiveTable = new LinkedHashMap<Integer, Map<Integer, TableOneCellOutput>>();

    // Table 2: Mapping Pressure Group to another map of surface interval minutes mapped to pressure group.
    Map<String, Map<Integer, String>> surfaceIntervalTimeTable = new LinkedHashMap<String, Map<Integer, String>>();

    // Table 3:
    Map<String, Map<Integer, Integer[]>> repetitiveDiveTimeTable = new LinkedHashMap<String, Map<Integer, Integer[]>>();

    String currentPressureGroup;

    public static void main(String[] args) {
        System.out.println("Hi");

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        map.put(3, 3);
        map.put(2, 2);
        map.put(5, 5);
        map.put(4, 4);
        map.put(1, 1);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        System.out.println();

        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry mapEntry = iter.next();
            System.out.println(mapEntry.getKey() + ", " + mapEntry.getValue());
        }
    }


    /**
     * Constructor that populates all three NAUI dive tables.
     */
    public DiveTable() {
        // Populate Table 1
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



        // Populate Table 2
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


        //Populate Table 3
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


    }

}



class TableOneCellOutput {
    String pressureGroup;
    Integer decompressionTime;

    public TableOneCellOutput(String PG) {
        this.pressureGroup = PG;
        this.decompressionTime = null;
    }

    public TableOneCellOutput(String PG, Integer DT) {
        this.pressureGroup = PG;
        this.decompressionTime = DT;
    }
}