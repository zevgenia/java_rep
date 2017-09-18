package ru.stqa.rep.addressbook.model;

public class ContactData {
  private int id;
  private final String firstname;
  private final String middlname;
  private final String lastname;
  private final String address;
  private final String mobile;
  private final String home;
  private final String email;
  private final String year;
  private final String note;
  private String group;

  public ContactData(int id, String firstname, String middlname, String lastname, String address, String mobile, String home, String email, String year, String note, String group) {
    this.id = id;
    this.firstname = firstname;
    this.middlname = middlname;
    this.lastname = lastname;
    this.address = address;
    this.mobile = mobile;
    this.home = home;
    this.email = email;
    this.year = year;
    this.note = note;
    this.group = group;
  }



  public ContactData(String firstname, String middlname, String lastname, String address, String mobile, String home, String email, String year, String note, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlname = middlname;
    this.lastname = lastname;
    this.address = address;
    this.mobile = mobile;
    this.home = home;
    this.email = email;
    this.year = year;
    this.note = note;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlname() {
    return middlname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getMobile() {
    return mobile;
  }

  public String getHome() {
    return home;
  }

  public String getEmail() {
    return email;
  }

  public String getYear() {
    return year;
  }

  public String getNote() {
    return note;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}