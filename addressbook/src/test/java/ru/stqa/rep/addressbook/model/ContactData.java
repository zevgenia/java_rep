package ru.stqa.rep.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlname;
  private final String lastname;
  private final String address;
  private final String mobile;
  private final String home;
  private final String email;
  private final String year;
  private final String note;
  private final String group;

  public ContactData(String firstname, String middlname, String lastname, String address, String mobile, String home, String email, String year, String note, String group) {
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
}
