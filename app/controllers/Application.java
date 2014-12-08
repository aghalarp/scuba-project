package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import utils.DiveTableCalc;
import views.formdata.*;
import views.html.*;


/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  public static String adminEmail;
  public static String adminPassword;

  /**
   * Returns the home page.
   * 
   * @return The resulting home page.
   */
  public static Result index() {

    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.isAdmin(ctx()), Secured.getUserInfo(ctx())));
  }


  /**
   * Provides the Login page (only to unauthenticated users).
   * 
   * @return The Login page.
   */
  public static Result login() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class);

    return ok(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.isAdmin(ctx()), Secured.getUserInfo(ctx()),
        formData));
  }

  /**
   * Processes a login form submission from an unauthenticated user. First we bind the HTTP POST data to an instance of
   * LoginFormData. The binding process will invoke the LoginFormData.validate() method. If errors are found, re-render
   * the page, displaying the error data. If errors not found, render the page with the good data.
   * 
   * @return The index page with the results of validation.
   */
  public static Result postLogin() {
    // Get the submitted form data from the request object, and run validation.
    Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();

    if (formData.hasErrors()) {
      flash("error", LoginFormData.ERROR_TEXT);

      return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.isAdmin(ctx()),
          Secured.getUserInfo(ctx()), formData));
    }
    else {
      // email/password OK, so now we set the session variable and only go to authenticated pages.
      session().clear();
      session("email", formData.get().email);
      return redirect(routes.Application.index());
    }
  }

  /**
   * Logs out (only for authenticated users) and returns them to the Index page.
   * 
   * @return A redirect to the Index page.
   */
  @Security.Authenticated(Secured.class)
  public static Result logout() {
    session().clear();
    return redirect(routes.Application.index());
  }


  /**
   * Displays the signup page.
   * 
   * @return Signup page.
   */
  public static Result signup() {
    Form<SignupFormData> formData = Form.form(SignupFormData.class);

    return ok(Signup.render("Signup", Secured.isLoggedIn(ctx()), Secured.isAdmin(ctx()), Secured.getUserInfo(ctx()),
        formData));
  }

  /**
   * Processes the signup page.
   * 
   * @return Signup page on fail, Login page on success.
   */
  public static Result postSignup() {
    Form<SignupFormData> formData = Form.form(SignupFormData.class).bindFromRequest();

    if (formData.hasErrors()) {
      flash("error", SignupFormData.ERROR_TEXT);

      return badRequest(Signup.render("Signup", Secured.isLoggedIn(ctx()), Secured.isAdmin(ctx()),
          Secured.getUserInfo(ctx()), formData));
    }
    else {
      // email/password OK, so now we set the session variable and only go to authenticated pages.
      UserInfoDB.addUserInfo(UserInfoDB.STANDARD, formData.get().email, formData.get().password);
      flash("success", SignupFormData.SUCCESS_TEXT);
      return redirect(routes.Application.login());
    }
  }






  public static Result newDivePlan() {
    DiveTableFormData data = new DiveTableFormData(); // Create empty surferFormData object
    Form<DiveTableFormData> diveTableFormData = Form.form(DiveTableFormData.class).fill(data); // Then fill it with form data.

    return ok(ShowDivePlan.render("NewDivePlan", Secured.isLoggedIn(ctx()), Secured.isAdmin(ctx()),
            Secured.getUserInfo(ctx()), diveTableFormData));
  }

  /**
   * Handles the posting of DivePlan form data by the user.
   *
   * @return The ShowDivePlan page with form data.
   */
  public static Result postDivePlan() {
    Form<DiveTableFormData> diveTableFormData = Form.form(DiveTableFormData.class).bindFromRequest();

    /*
     * Important to understand: Whenever we invoke bindFromRequest(), if there is a validation() method in the
     * associated object's class (in this case, DiveTableFormData) that implements the form, it will call that validation
     * method and annotate that object with information about any found errors (We see that the validate() method
     * returns either null or a list of ValidationErrors). We can then check if the formData object contains any errors
     * by calling the hasErrors() method, as seen below.
     *
     * Also remember: We cannot call the get() method if there are errors in the formData object, so we put it in the
     * else clause.
     */
    if (diveTableFormData.hasErrors()) {
      //Errors found, so re-display form.

      return badRequest(ShowDivePlan.render("NewDivePlan", Secured.isLoggedIn(ctx()), Secured.isAdmin(ctx()),
              Secured.getUserInfo(ctx()), diveTableFormData));
    }
    else {
      DiveTableFormData data = diveTableFormData.get(); // Creates the object we made (DiveTableFormData) and fills with get data
      // Add/Save to database
      DiveTableDB.addDiveTable(data);
      UserInfoDB.modifyUserDiveTableList(Secured.getUserInfo(ctx()), data.eventID);
      return redirect("/diveplan/" + data.eventID);
    }
  }

  /**
   * Deletes requested DiveTable from database.
   *
   * @param eventID The eventID of the DiveTable to delete.
   * @return The index page.
   */
  @Security.Authenticated(Secured.class)
  public static Result deleteDiveTable(String eventID) {
    DiveTableDB.deleteDiveTable(eventID);
    return redirect("/");
  }

  /**
   * Returns the given DivePlan page.
   *
   * @param eventID The eventID of the DivePlan
   * @return The requested DivePlan page.
   */
  public static Result getDivePlan(String eventID) {
    DiveTable diveTable = DiveTableDB.getDiveTable(eventID);

    //Must create map of dive results to pass into view.
    //First calculate results of given dive table.
    DiveTableCalc diveTableCalc = computeDiveTableCalc(diveTable);
    diveTableCalc.createPlainResultsMap(); //Call this method to create a plainer map of results.
    //Btw, Don't have to worry about error checking, because already did that during form submission.

    Map<Integer, List<String>> diveTableCalcMap = diveTableCalc.plainResultsMap;
    String combinedListValues = "";
    for (int i=1; i <= diveTableCalcMap.size(); i++) {
      List<String> temp = diveTableCalcMap.get(i);

      for (String s: temp) {
        combinedListValues += s + ",";
      }
    }


    return ok(ShowSingleDivePlan.render("ShowDivePlan", Secured.isLoggedIn(ctx()), Secured.isAdmin(ctx()),
            Secured.getUserInfo(ctx()), DiveTableDB.getDiveTable(eventID), diveTableCalcMap, combinedListValues));
  }

  /**
   * Will populate input data into arraylist, and then use it to calculate dive table results.
   *
   * @return DiveTableCalc The object holding dive results.
   */
  public static DiveTableCalc computeDiveTableCalc (DiveTable diveTable) {
    //First, determine how many dives there are.
    int diveCount = 0;
    if (diveTable.getDepth1() != null && !diveTable.getDepth1().trim().equals("")) {
      diveCount++;
    }
    if (diveTable.getDepth2() != null && !diveTable.getDepth2().trim().equals("")) {
      diveCount++;
    }
    if (diveTable.getDepth3() != null && !diveTable.getDepth3().trim().equals("")) {
      diveCount++;
    }
    if (diveTable.getDepth4() != null && !diveTable.getDepth4().trim().equals("")) {
      diveCount++;
    }
    if (diveTable.getDepth5() != null && !diveTable.getDepth5().trim().equals("")) {
      diveCount++;
    }

    //Populate list holding all dive input data. Will use with DiveTableCalc later.
    List<Integer> diveDataList = new ArrayList<Integer>();
    for (int i=0; i < diveCount; i++) {
      if (i == 0) {
        diveDataList.add(Integer.parseInt(diveTable.getDepth1()));
        diveDataList.add(Integer.parseInt(diveTable.getBottomTime1()));
        diveDataList.add(Integer.parseInt(diveTable.getSurfaceIntervalTime1()));
      }
      if (i == 1) {
        diveDataList.add(Integer.parseInt(diveTable.getDepth2()));
        diveDataList.add(Integer.parseInt(diveTable.getBottomTime2()));
        diveDataList.add(Integer.parseInt(diveTable.getSurfaceIntervalTime2()));
      }
      if (i == 2) {
        diveDataList.add(Integer.parseInt(diveTable.getDepth3()));
        diveDataList.add(Integer.parseInt(diveTable.getBottomTime3()));
        diveDataList.add(Integer.parseInt(diveTable.getSurfaceIntervalTime3()));
      }
      if (i == 3) {
        diveDataList.add(Integer.parseInt(diveTable.getDepth4()));
        diveDataList.add(Integer.parseInt(diveTable.getBottomTime4()));
        diveDataList.add(Integer.parseInt(diveTable.getSurfaceIntervalTime4()));
      }
      if (i == 4) {
        diveDataList.add(Integer.parseInt(diveTable.getDepth5()));
        diveDataList.add(Integer.parseInt(diveTable.getBottomTime5()));
        diveDataList.add(Integer.parseInt(diveTable.getSurfaceIntervalTime5()));
      }
    }

    //Calculate and return dive object;
    DiveTableCalc dive = new DiveTableCalc();
    dive.calculateDiveTableResults(diveDataList);

    return dive;
  }

  /**
   * Returns the Dashboard page.
   *
   * @return Dashboard page.
   */
  public static Result getDashboard() {
    return ok(Dashboard.render("ShowDashboard", Secured.isLoggedIn(ctx()), Secured.isAdmin(ctx()),
            Secured.getUserInfo(ctx()), DiveTableDB.getDiveTables()));
  }
}
