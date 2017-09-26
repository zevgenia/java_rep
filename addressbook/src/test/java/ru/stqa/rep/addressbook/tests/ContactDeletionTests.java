package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void Precondition() {
    if (app.contact().all().size() == 0) {
      app.goTo().gotoNewContact();
      app.contact().create(new ContactData().withFirstname("Надежда").withMiddlname("Ивановна").withLastname("Сидорова")
              .withAddress("ул.Изюмская, д.1, кв.130").withMobile("+7(499)123-12-12")
              .withHome("+7(495)123-12-12").withWork("+7(495)555-55-55").withEmail("222@mail.ru").withYear("1980")
              .withNote("домофон 130").withGroup("Друзья"), true);
    }
  }

  @Test(enabled = true)

  public void ContactDeletion() {

    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();

    app.contact().delete(deletedContact);
    app.goTo().groupPage();
    app.goTo().gotoHomePage();
    assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
