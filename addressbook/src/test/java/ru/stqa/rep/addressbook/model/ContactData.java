package ru.stqa.rep.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = 0;

  @Expose
  @Column(name = "firstname")
  private String firstname = "";

  @Expose
  @Column(name = "middlename")
  private String middlname = "";

  @Expose
  @Column(name = "lastname")
  private String lastname = "";

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address = "";

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile = "";

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String home = "";

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String work = "";

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email = "";

  @Transient
//  @Column(name = "byear")
//  @Type(type = "text")
  private String year;

  @Expose
  @Column(name = "notes")
  @Type(type = "text")
  private String note = "";

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

  @Expose
  @ManyToMany(fetch = FetchType.EAGER) //из БД будет извлекаться как можно больше инф. за одно обращение
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"),
          inverseJoinColumns = @JoinColumn(name = "group_id"))

  private Set<GroupData> groups = new HashSet<GroupData>();

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

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withWork(String work) {
    this.work = work;
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

  public ContactData inGroup(GroupData group) { //добавляет контакт в группу с именем в переменной group
    groups.add(group);
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

  // метод позволяет получить список групп, в которые входит данный контакт
  public Groups getGroups() {
    return new Groups(groups);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (middlname != null ? !middlname.equals(that.middlname) : that.middlname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    if (home != null ? !home.equals(that.home) : that.home != null) return false;
    if (work != null ? !work.equals(that.work) : that.work != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (note != null ? !note.equals(that.note) : that.note != null) return false;
    return groups != null ? groups.equals(that.groups) : that.groups == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (middlname != null ? middlname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    result = 31 * result + (home != null ? home.hashCode() : 0);
    result = 31 * result + (work != null ? work.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (note != null ? note.hashCode() : 0);
    result = 31 * result + (groups != null ? groups.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", middlname='" + middlname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", mobile='" + mobile + '\'' +
            ", home='" + home + '\'' +
            ", work='" + work + '\'' +
            ", email='" + email + '\'' +
            ", note='" + note + '\'' +
            ", groups=" + groups +
            '}';
  }
}