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
//    app.goTo().groupPage();
    app.goTo().gotoHomePage();
    if (app.Contact().all().size() == 0) {
      app.goTo().gotoNewContact();
      app.Contact().create(new ContactData().withFirstname("Надежда").withMiddlname("Ивановна")
              .withLastname("Сидорова").withAddress("ул.Изюмская, д.1, кв.130")
              .withMobile("+7(000)123-12-12").withHome("+7(495)123-12-12")
              .withEmail("222@mail.ru").withYear("1980").withNote("домофон 130").withGroup("Друзья"), true);
    }
  }

  @Test(enabled = true)

  public void ContactModification() {
    Contacts before = app.Contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Анна")
            .withMiddlname("Ивановна").withLastname("Маркова").withAddress("ул.Изюмская, д.1, кв.130")
            .withMobile("+7(000)123-12-12").withHome("+7(495)123-12-12")
            .withEmail("222@mail.ru").withYear("1980").withNote("домофон 130").withGroup("Друзья");

    app.Contact().modify(contact);
    assertEquals(app.Contact().count(), before.size());
    Contacts after = app.Contact().all();
    assertThat(after, equalTo(
            before.without(modifiedContact).withAdded(contact)));
  }
}
