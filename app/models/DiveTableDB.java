package models;

import java.util.ArrayList;
import java.util.List;

import views.formdata.DiveTableFormData;

/**
 * A repository/database for DiveTable data.
 *
 * @author Dave
 *
 */
public class DiveTableDB {

    /**
     * Adds or edits a DiveTable object to the database.
     *
     * @param formData The divetable data.
     * @return The newly created DiveTable.
     */
    public static DiveTable addDiveTable(DiveTableFormData formData) {

        DiveTable diveTable = getDiveTable(formData.eventID);

        //Since not a new diveTable, we dont change eventID.
        if (diveTable != null) {
            diveTable.setLocation(formData.location);
            diveTable.setDate(formData.date);
            diveTable.setDepth1(formData.depth1);
            diveTable.setBottomTime1(formData.bottomTime1);
            diveTable.setSurfaceIntervalTime1(formData.surfaceIntervalTime1);
            diveTable.setDepth2(formData.depth2);
            diveTable.setBottomTime2(formData.bottomTime2);
            diveTable.setSurfaceIntervalTime2(formData.surfaceIntervalTime2);
            diveTable.setDepth3(formData.depth3);
            diveTable.setBottomTime3(formData.bottomTime3);
            diveTable.setSurfaceIntervalTime3(formData.surfaceIntervalTime3);
            diveTable.setDepth4(formData.depth4);
            diveTable.setBottomTime4(formData.bottomTime4);
            diveTable.setSurfaceIntervalTime4(formData.surfaceIntervalTime4);
            diveTable.setDepth5(formData.depth5);
            diveTable.setBottomTime5(formData.bottomTime5);
            diveTable.setSurfaceIntervalTime5(formData.surfaceIntervalTime5);
            diveTable.setDiveTableCalcErrorMessage(formData.diveTableCalcErrorMessage);
        }
        else {
            diveTable =
                    new DiveTable(formData.eventID, formData.location, formData.date, formData.depth1, formData.bottomTime1,
                            formData.surfaceIntervalTime1, formData.depth2, formData.bottomTime2, formData.surfaceIntervalTime2,
                            formData.depth3, formData.bottomTime3, formData.surfaceIntervalTime3, formData.depth4,
                            formData.bottomTime4, formData.surfaceIntervalTime4, formData.depth5, formData.bottomTime5,
                            formData.surfaceIntervalTime5, formData.diveTableCalcErrorMessage);
        }

        //Save to DB
        diveTable.save();

        return diveTable;
    }


    /**
     * Checks if DiveTable eventID already exists.
     *
     * @param eventID The eventID to check.
     * @return True if eventID exists, False otherwise.
     */
    public static boolean eventIDExists(String eventID) {
        return DiveTable.find().where().eq("eventID", eventID).findUnique() != null;
    }

    /**
     * Returns a DiveTable instance associated with the passed eventID.
     *
     * @param eventID The eventID.
     * @return The retrieved DiveTable object.
     */
    public static DiveTable getDiveTable(String eventID) {
        return DiveTable.find().where().eq("eventID", eventID).findUnique();
    }

    /**
     * Returns a list containing all defined DiveTables.
     *
     * @return A list of DiveTable objects.
     */
    public static List<DiveTable> getDiveTables() {
        return DiveTable.find().all();
    }

    /**
     * Deletes the specified DiveTable.
     *
     * @param eventID The eventID associated with the DiveTable to delete.
     */
    public static void deleteDiveTable(String eventID) {
        DiveTable diveTable = getDiveTable(eventID);
        if (diveTable != null) {
            diveTable.delete();
        }
    }


}
