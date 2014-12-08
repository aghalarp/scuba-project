package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import play.db.ebean.Model;
import utils.DiveTableCalc;

/**
 * DiveTable class.
 *
 * @author David A.
 *
 */
@Entity
public class DiveTable extends Model {


    /**
     * The EBean ORM finder method for database queries on ID.
     *
     * @return The finder method for Surfers.
     */
    public static Finder<Long, DiveTable> find() {
        return new Finder<Long, DiveTable>(Long.class, DiveTable.class);
    }

    @Id
    private long id;

    private String eventID;
    private String location;
    private String date;
    private String depth1;
    private String bottomTime1;
    private String surfaceIntervalTime1;
    private String depth2;
    private String bottomTime2;
    private String surfaceIntervalTime2;
    private String depth3;
    private String bottomTime3;
    private String surfaceIntervalTime3;
    private String depth4;
    private String bottomTime4;
    private String surfaceIntervalTime4;
    private String depth5;
    private String bottomTime5;
    private String surfaceIntervalTime5;
    private String diveTableCalcErrorMessage;


    //private Boolean isEditable = false;


    @ManyToMany(mappedBy = "divetables", cascade = CascadeType.ALL )
    private List<UserInfo> userInfos = new ArrayList<>();

    /**
     * Creates a new DiveTable.
     *
     */
    public DiveTable(String eventID, String location, String date, String depth1, String bottomTime1, String surfaceIntervalTime1,
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


    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepth1() {
        return depth1;
    }

    public void setDepth1(String depth1) {
        this.depth1 = depth1;
    }

    public String getBottomTime1() {
        return bottomTime1;
    }

    public void setBottomTime1(String bottomTime1) {
        this.bottomTime1 = bottomTime1;
    }

    public String getSurfaceIntervalTime1() {
        return surfaceIntervalTime1;
    }

    public void setSurfaceIntervalTime1(String surfaceIntervalTime1) {
        this.surfaceIntervalTime1 = surfaceIntervalTime1;
    }

    public String getDepth2() {
        return depth2;
    }

    public void setDepth2(String depth2) {
        this.depth2 = depth2;
    }

    public String getBottomTime2() {
        return bottomTime2;
    }

    public void setBottomTime2(String bottomTime2) {
        this.bottomTime2 = bottomTime2;
    }

    public String getSurfaceIntervalTime2() {
        return surfaceIntervalTime2;
    }

    public void setSurfaceIntervalTime2(String surfaceIntervalTime2) {
        this.surfaceIntervalTime2 = surfaceIntervalTime2;
    }

    public String getDepth3() {
        return depth3;
    }

    public void setDepth3(String depth3) {
        this.depth3 = depth3;
    }

    public String getBottomTime3() {
        return bottomTime3;
    }

    public void setBottomTime3(String bottomTime3) {
        this.bottomTime3 = bottomTime3;
    }

    public String getSurfaceIntervalTime3() {
        return surfaceIntervalTime3;
    }

    public void setSurfaceIntervalTime3(String surfaceIntervalTime3) {
        this.surfaceIntervalTime3 = surfaceIntervalTime3;
    }

    public String getDepth4() {
        return depth4;
    }

    public void setDepth4(String depth4) {
        this.depth4 = depth4;
    }

    public String getBottomTime4() {
        return bottomTime4;
    }

    public void setBottomTime4(String bottomTime4) {
        this.bottomTime4 = bottomTime4;
    }

    public String getSurfaceIntervalTime4() {
        return surfaceIntervalTime4;
    }

    public void setSurfaceIntervalTime4(String surfaceIntervalTime4) {
        this.surfaceIntervalTime4 = surfaceIntervalTime4;
    }

    public String getDepth5() {
        return depth5;
    }

    public void setDepth5(String depth5) {
        this.depth5 = depth5;
    }

    public String getBottomTime5() {
        return bottomTime5;
    }

    public void setBottomTime5(String bottomTime5) {
        this.bottomTime5 = bottomTime5;
    }

    public String getSurfaceIntervalTime5() {
        return surfaceIntervalTime5;
    }

    public void setSurfaceIntervalTime5(String surfaceIntervalTime5) {
        this.surfaceIntervalTime5 = surfaceIntervalTime5;
    }

    public String getDiveTableCalcErrorMessage() {
        return diveTableCalcErrorMessage;
    }

    public void setDiveTableCalcErrorMessage(String diveTableCalcErrorMessage) {
        this.diveTableCalcErrorMessage = diveTableCalcErrorMessage;
    }


    /**
     * @return users.
     */
    public List<UserInfo> getUsers() {
        return this.userInfos;
    }
}
