package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test

  public void ContactDeletion() {

    app.getNavigationHelper().gotoHomePage();

    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoNewContact();
      app.getContactHelper().createContact(new ContactData("Надежда", "Ивановна", "Сидорова", "ул.Изюмская, д.1, кв.130", "+7(000)123-12-12", "+7(495)123-12-12", "222@mail.ru", "1980", "домофон 130", "Друзья"), true);
    }
    int before = app.getContactHelper().getContactCount();
    System.out.println("Количество контактов до " + before);

    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHomePage();

    int after = app.getContactHelper().getContactCount();
    System.out.println("Количество контактов после " + after);
    Assert.assertEquals(after, before - 1);

  }
}
