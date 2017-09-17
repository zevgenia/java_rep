package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test

  public void ContactDeletion() {

    app.getNavigationHelper().gotoHomePage();

    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoNewContact();
      app.getContactHelper().createContact(new ContactData("Надежда", "Ивановна", "Сидорова",
              "ул.Изюмская, д.1, кв.130", "+7(000)123-12-12", "+7(495)123-12-12", "222@mail.ru",
              "1980", "домофон 130", "Друзья"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    System.out.println("Количество контактов до " + before.size());

    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    System.out.println("Количество контактов после " + after.size());
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    for ( int i = 0; i < after.size(); i++ ) {
      Assert.assertEquals(before.get(i), after.get(i));
      System.out.println("сравниваем " + before.get(i)+ " и "+after.get(i));
    }

  }
}
