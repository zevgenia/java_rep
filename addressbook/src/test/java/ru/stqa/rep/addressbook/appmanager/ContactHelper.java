package ru.stqa.rep.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnHomePage() {
    click(By.xpath("//div[@class='msgbox']//a[.='home page']"));
  }

  public void submitContactForm() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    type(By.name("byear"), contactData.getYear());
    type(By.name("notes"), contactData.getNote());

    if (creation) {
//      new Select(wd.findElement(By.name("new_group"))).selectByIndex(0); //нет списка групп
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup()); //есть список групп
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void initContactModification(int index) {
    click(By.xpath("//table[@id='maintable']/tbody/tr["+(index+2)+"]/td[8]/a/img"));
  }

  public void updateContactForm() {
    click(By.name("update"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();

  }

  public void createContact(ContactData contact, boolean b) {
    fillContactForm(contact, b);
    submitContactForm();
    returnHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();

  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement>elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements){
      String name = element.getText();
      ContactData contact = new ContactData("firstname", "middlename","lastname",
              "address","mobile","home","email","year","note","group" );
      contacts.add(contact);
    }

    return contacts;
  }
}
