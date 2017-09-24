package ru.stqa.rep.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void Precondition() {
    if (app.Contact().all().size() == 0) {
      app.goTo().gotoNewContact();
      app.Contact().create(new ContactData().withFirstname("Надежда").withMiddlname("Ивановна").withLastname("Сидорова")
              .withAddress("ул.Изюмская, д.1, кв.130").withMobile("+7(000)123-12-12")
              .withHome("+7(495)123-12-12").withEmail("222@mail.ru").withYear("1980")
              .withNote("домофон 130").withGroup( "Друзья"), true);
    }
  }

  @Test(enabled = true)

  public void ContactDeletion() {

    app.goTo().gotoHomePage();
    Contacts before = app.Contact().all();
    ContactData deletedContact = before.iterator().next();

    app.Contact().delete(deletedContact);
    app.goTo().groupPage();
    app.goTo().gotoHomePage();
    Contacts after = app.Contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
