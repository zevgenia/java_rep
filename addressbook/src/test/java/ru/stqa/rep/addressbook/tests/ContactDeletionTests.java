package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void Precondition() {
    if (app.Contact().List().size() == 0) {
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
    List<ContactData> before = app.Contact().List();
    int index = before.size() - 1;
    app.Contact().delete(index);
    app.goTo().groupPage();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.Contact().List();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
