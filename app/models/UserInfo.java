package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import play.db.ebean.Model;

/**
 * A simple representation of a user.
 *
 * @author David A.
 */
@Entity
public class UserInfo extends Model {

  private static final long serialVersionUID = -2665795787864719439L;

  /**
   * The EBean ORM finder method for database queries on ID.
   * 
   * @return The finder method for UserInfo.
   */
  public static Finder<Long, UserInfo> find() {
    return new Finder<Long, UserInfo>(Long.class, UserInfo.class);
  }

  @Id
  private long id;

  private String type;
  private String email;
  private String password;


  //Represents the many divetable events of the user.
  @ManyToMany(cascade = CascadeType.ALL)
  private List<DiveTable> divetables = new ArrayList<>();

  /**
   * Creates a new UserInfo instance.
   * 
   * @param type The name.
   * @param email The email.
   * @param password The password.
   */
  public UserInfo(String type, String email, String password) {
    this.type = type;
    this.email = email;
    this.password = password;
  }

  /**
   * @return the name
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the name to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }


  public List<DiveTable> getDiveTables() {
    return this.divetables;
  }

}
