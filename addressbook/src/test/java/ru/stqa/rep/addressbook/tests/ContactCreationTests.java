package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test


  public void testContactCreation() {
//    app.goTo().groupPage();
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    app.gotoNewContact();
    File photo = new File("src/test/resources/Koala.jpg");

    ContactData contact = new ContactData().withFirstname("Надежда").withMiddlname("Ивановна")
            .withLastname("Сидорова").withPhoto(photo).withAddress("ул.Изюмская, д.1, кв.130")
            .withMobile("+7(499)123-12-12").withHome("+7(495)123-12-12").withWork("+7(495)555-55-55")
            .withEmail("222@mail.ru").withYear("1980").withNote("домофон 130").withGroup("Друзья");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
  @Test (enabled = false)
  public void  testCurrentDir() {
    File currentDir = new File(".");
    File photo = new File("src/test/resources/pic2.PNG");
    System.out.println(currentDir.getAbsolutePath());
    System.out.println( photo.exists());
  }

}
