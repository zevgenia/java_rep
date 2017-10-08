package ru.stqa.rep.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.math.BigInteger;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstname;

  @Expose
  @Column(name = "middlename")
  private String middlname;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String home;

  @Column(name = "work")
  @Type(type = "text")
  private String work;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Transient
//  @Column(name = "byear")
//  @Type(type = "text")
  private String year;

  @Expose
  @Column(name = "notes")
  @Type(type = "text")
  private String note;

  @Expose
  @Transient
  private String group;
  @Transient
  private String allPhones;
  @Transient
  private String allEmails;

  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlname(String middlname) {
    this.middlname = middlname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withHome(String home) {
    this.home = home;
    return this;
  }

  public ContactData withWork(String work) {
    this.work = work;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withYear(String year) {
    this.year = year;
    return this;
  }

  public ContactData withNote(String note) {
    this.note = note;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }


  public int getId() {
    return id;
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

  public String getWork() {
    return work;
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

  public String getAllPhones() {
    return allPhones;
  }

  public String getEmail3() {
    return email3;
  }

  public String getEmail2() {
    return email2;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public File getPhoto() {
    return new File(photo);
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

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}