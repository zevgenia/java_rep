package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod

    public void endsurePrecondition() {
    app.goTo().gotoHomePage();
    if (app.Contact().List().size() == 0) {
      app.goTo().gotoNewContact();
      app.Contact().create(new ContactData().withFirstname("Надежда").withMiddlname("Ивановна")
                      .withLastname("Сидорова").withAddress("ул.Изюмская, д.1, кв.130")
                      .withMobile("+7(000)123-12-12").withHome("+7(495)123-12-12")
                      .withEmail("222@mail.ru").withYear("1980").withNote("домофон 130").withGroup("Друзья"), true);
    }
  }

  @Test(enabled = true)

  public void ContactModification() {
    List<ContactData> before = app.Contact().List();
    ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).withFirstname("Анна")
            .withMiddlname("Ивановна").withLastname("Макрова").withAddress("ул.Изюмская, д.1, кв.130")
            .withMobile("+7(000)123-12-12").withHome("+7(495)123-12-12")
            .withEmail("222@mail.ru").withYear("1980").withNote("домофон 130").withGroup("Друзья");

    int index = before.size() - 1;
    app.Contact().modify(contact, index);

    List<ContactData> after = app.Contact().List();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);

  }



}
