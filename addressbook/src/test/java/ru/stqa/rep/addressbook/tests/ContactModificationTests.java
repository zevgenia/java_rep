package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod

  public void endsurePrecondition() {

    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHomePage();
      app.goTo().gotoNewContact();
      app.contact().create(new ContactData().withFirstname("Надежда").withMiddlname("Ивановна")
              .withLastname("Сидорова").withAddress("ул.Изюмская, д.1, кв.130")
              .withMobile("+7(499)123-12-12").withHome("+7(495)123-12-12").withWork("+7(495)555-55-55")
              .withEmail("222@mail.ru").withNote("домофон 130"), true);
    }
  }

  @Test(enabled = true)

  public void ContactModification() {
    Contacts before = app.db().contacts();

    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Анна")
            .withMiddlname("Ивановна").withLastname("Маркова").withAddress("ул.Изюмская, д.1, кв.130")
            .withMobile("+7(499)123-12-12").withHome("+7(495)123-12-12").withWork("+7(495)555-55-55")
            .withEmail("222@mail.ru").withNote("домофон 130");

    app.goTo().gotoHomePage();
    app.contact().modify(contact);

    Contacts after = app.db().contacts();
    System.out.println("БЫЛО: "+ before.size()+ " СТАЛО: " +after.size());
    assertEquals(after.size(), (before.size()));

    assertThat(after, equalTo(
            before.without(modifiedContact).withAdded(contact)));

    verifyContactListInUI(); //новая проверка
  }
}
