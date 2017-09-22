package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test (enabled =  true)


  public void testContactCreation() {
//    app.goTo().groupPage();
    app.goTo().gotoHomePage();
    List<ContactData> before = app.Contact().List();
    app.gotoNewContact();
    ContactData contact = new ContactData().withFirstname("Надежда").withMiddlname("Ивановна")
            .withLastname("Сидорова").withAddress("ул.Изюмская, д.1, кв.130")
            .withMobile("+7(000)123-12-12").withHome("+7(495)123-12-12")
            .withEmail("222@mail.ru").withYear("1980").withNote("домофон 130").withGroup("Друзья");
    app.Contact().create(contact, true);

    List<ContactData> after = app.Contact().List();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2) -> Integer.compare(c1.getId(), c2.getId()) ;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
