package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

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
    Set<ContactData> before = app.Contact().all();
    ContactData deletedContact = before.iterator().next();

    app.Contact().delete(deletedContact);
    app.goTo().groupPage();
    app.goTo().gotoHomePage();
    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }


}
