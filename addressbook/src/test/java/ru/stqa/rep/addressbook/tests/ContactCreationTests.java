package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    System.out.println("Количество контактов до " + before.size());
    app.gotoNewContact();
    ContactData contact = new ContactData ("Надежда", "Ивановна", "Сидорова",
            "ул.Изюмская, д.1, кв.130", "+7(000)123-12-12", "+7(495)123-12-12", "222@mail.ru",
            "1980", "домофон 130", "Друзья");
    app.getContactHelper().createContact(contact, true);

    List<ContactData> after = app.getContactHelper().getContactList();
    System.out.println("Количество контактов после " + after.size());
    Assert.assertEquals(after.size(), before.size() + 1);

    int max =0;
    for (ContactData c : after){
      if (c.getId() > max){
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

  }
}
