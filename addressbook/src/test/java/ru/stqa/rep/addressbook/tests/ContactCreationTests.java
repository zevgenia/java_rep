package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    System.out.println("Количество контактов до " + before);
    app.gotoNewContact();
    app.getContactHelper().createContact(new ContactData("Надежда", "Ивановна", "Сидорова", "ул.Изюмская, д.1, кв.130", "+7(000)123-12-12", "+7(495)123-12-12", "222@mail.ru", "1980", "домофон 130", "Друзья"),true);
    int after = app.getContactHelper().getContactCount();
    System.out.println("Количество контактов после " + after);
    Assert.assertEquals(after, before + 1);

  }
}
