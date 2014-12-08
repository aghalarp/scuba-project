package views.formdata;

import java.util.ArrayList;
import java.util.List;

import models.DiveTable;
import models.DiveTableDB;
import play.data.validation.ValidationError;

import utils.DiveTableCalc;

/**
 * Created by David.
 */
public class DiveTableFormData {

    public String eventID = ""; //Serves as unique id (slug) for the dive event. Used in GET urls.
    public String location = "";
    public String date = "";

    public String depth1 = "";
    public String bottomTime1 = "";
    public String surfaceIntervalTime1 = "";

    public String depth2 = "";
    public String bottomTime2 = "";
    public String surfaceIntervalTime2 = "";

    public String depth3 = "";
    public String bottomTime3 = "";
    public String surfaceIntervalTime3 = "";

    public String depth4 = "";
    public String bottomTime4 = "";
    public String surfaceIntervalTime4 = "";

    public String depth5 = "";
    public String bottomTime5 = "";
    public String surfaceIntervalTime5 = "";

    public String diveTableCalcErrorMessage = "";


    /** The isEditable hidden form field. For seeing if allowed to edit */
   // public boolean isEditable = false;


    /**
     * Default constructor, required by Play.
     */
    public DiveTableFormData() {
        // Nothing needed.
    }

    /**
     * Constructs a DiveTableFormData object manually. Currently only used by Global.java to create initial Surfers on
     * application startup. NOTE: This doesn't currently check if eventID already exists. Use with care.
     *
     */
    public DiveTableFormData(String eventID, String location, String date, String depth1, String bottomTime1, String surfaceIntervalTime1,
                     String depth2, String bottomTime2, String surfaceIntervalTime2, String depth3, String bottomTime3,
                     String surfaceIntervalTime3, String depth4, String bottomTime4, String surfaceIntervalTime4,
                     String depth5, String bottomTime5, String surfaceIntervalTime5, String diveTableCalcErrorMessage) {

        this.eventID = eventID;
        this.location = location;
        this.date = date;
        this.depth1 = depth1;
        this.bottomTime1 = bottomTime1;
        this.surfaceIntervalTime1 = surfaceIntervalTime1;
        this.depth2 = depth2;
        this.bottomTime2 = bottomTime2;
        this.surfaceIntervalTime2 = surfaceIntervalTime2;
        this.depth3 = depth3;
        this.bottomTime3 = bottomTime3;
        this.surfaceIntervalTime3 = surfaceIntervalTime3;
        this.depth4 = depth4;
        this.bottomTime4 = bottomTime4;
        this.surfaceIntervalTime4 = surfaceIntervalTime4;
        this.depth5 = depth5;
        this.bottomTime5 = bottomTime5;
        this.surfaceIntervalTime5 = surfaceIntervalTime5;
        this.diveTableCalcErrorMessage = diveTableCalcErrorMessage;
    }

    /**
     * Create a DiveTableFormData object based upon a DiveTable.
     *
     * @param diveTable The DiveTable object.
     */
    public DiveTableFormData(DiveTable diveTable) {
        this.eventID = diveTable.getEventID();
        this.location = diveTable.getLocation();
        this.date = diveTable.getDate();
        this.depth1 = diveTable.getDepth1();
        this.bottomTime1 = diveTable.getBottomTime1();
        this.surfaceIntervalTime1 = diveTable.getSurfaceIntervalTime1();
        this.depth2 = diveTable.getDepth2();
        this.bottomTime2 = diveTable.getBottomTime2();
        this.surfaceIntervalTime2 = diveTable.getSurfaceIntervalTime2();
        this.depth3 = diveTable.getDepth3();
        this.bottomTime3 = diveTable.getBottomTime3();
        this.surfaceIntervalTime3 = diveTable.getSurfaceIntervalTime3();
        this.depth4 = diveTable.getDepth4();
        this.bottomTime4 = diveTable.getBottomTime4();
        this.surfaceIntervalTime4 = diveTable.getSurfaceIntervalTime4();
        this.depth5 = diveTable.getDepth5();
        this.bottomTime5 = diveTable.getBottomTime5();
        this.surfaceIntervalTime5 = diveTable.getSurfaceIntervalTime5();
        this.diveTableCalcErrorMessage = diveTable.getDiveTableCalcErrorMessage();
    }

    /**
     * Will populate input data into arraylist, and then use it to calculate dive table results.
     *
     * @return DiveTableCalc The object holding dive results.
     */
    public DiveTableCalc computeDiveTableCalc () {
        //First, determine how many dives there are.
        int diveCount = 0;
        if (depth1 != null && !depth1.trim().equals("")) {
            diveCount++;
        }
        if (depth2 != null && !depth2.trim().equals("")) {
            diveCount++;
        }
        if (depth3 != null && !depth3.trim().equals("")) {
            diveCount++;
        }
        if (depth4 != null && !depth4.trim().equals("")) {
            diveCount++;
        }
        if (depth5 != null && !depth5.trim().equals("")) {
            diveCount++;
        }

        //Populate list holding all dive input data. Will use with DiveTableCalc later.
        List<Integer> diveDataList = new ArrayList<Integer>();
        for (int i=0; i < diveCount; i++) {
            if (i == 0) {
                diveDataList.add(Integer.parseInt(depth1));
                diveDataList.add(Integer.parseInt(bottomTime1));
                diveDataList.add(Integer.parseInt(surfaceIntervalTime1));
            }
            if (i == 1) {
                diveDataList.add(Integer.parseInt(depth2));
                diveDataList.add(Integer.parseInt(bottomTime2));
                diveDataList.add(Integer.parseInt(surfaceIntervalTime2));
            }
            if (i == 2) {
                diveDataList.add(Integer.parseInt(depth3));
                diveDataList.add(Integer.parseInt(bottomTime3));
                diveDataList.add(Integer.parseInt(surfaceIntervalTime3));
            }
            if (i == 3) {
                diveDataList.add(Integer.parseInt(depth4));
                diveDataList.add(Integer.parseInt(bottomTime4));
                diveDataList.add(Integer.parseInt(surfaceIntervalTime4));
            }
            if (i == 4) {
                diveDataList.add(Integer.parseInt(depth5));
                diveDataList.add(Integer.parseInt(bottomTime5));
                diveDataList.add(Integer.parseInt(surfaceIntervalTime5));
            }
        }

        //Calculate and return dive object;
        DiveTableCalc dive = new DiveTableCalc();
        dive.calculateDiveTableResults(diveDataList);

        return dive;
    }

    /**
     * Validates the form input by the user. The following fields must be non-empty: Name, Home, Carousel URL, Bio URL,
     * Biography. The Awards field is optional. It can be empty. The Slug field must be alphanumeric, and must be unique.
     * The SurferType field must be either "Male", "Female", or "Grom". The footstyleType field must be either "Regular"
     * or "Goofy".
     *
     *
     * @return null if no errors, list of ValidationErrors if errors.
     */

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        if (eventID == null || eventID.length() == 0) {
            errors.add(new ValidationError("eventID", "eventID is required"));
        }

        if (!eventID.matches("^[a-zA-Z0-9]+$")) { // Checks if string is alphanumeric. Also checks if string is empty.
            errors.add(new ValidationError("eventID", "EventID must only contain letters and digits."));
        }

        if (DiveTableDB.eventIDExists(eventID)) {
            errors.add(new ValidationError("eventID", "EventID already exists"));
        }

        if (location == null || location.length() == 0) {
            errors.add(new ValidationError("location", "Location is required"));
        }

        if (date == null || date.length() == 0) {
            errors.add(new ValidationError("date", "Date is required"));
        }

        if (depth1 == null || depth1.length() == 0) {
            errors.add(new ValidationError("depth1", "Depth-1 is required"));
        }

        if (bottomTime1 == null || bottomTime1.length() == 0) {
            errors.add(new ValidationError("bottomTime1", "BottomTime-1 is required"));
        }

        if (surfaceIntervalTime1 == null || surfaceIntervalTime1.length() == 0) {
            errors.add(new ValidationError("surfaceIntervalTime1", "SurfaceIntervalTime-1 is required"));
        }

        DiveTableCalc dive = computeDiveTableCalc();
        if (dive.errorFound > 0) {
            errors.add(new ValidationError("diveTableCalcErrorMessage", "Error found at Dive #" + dive.errorFound + ": " + dive.errorMessage));
        }


//        if (isEditable == false && errors.isEmpty()) {
//            isEditable = true;
//        }

        return errors.isEmpty() ? null : errors;
    }
}
