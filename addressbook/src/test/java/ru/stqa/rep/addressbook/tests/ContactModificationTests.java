package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrcondition() {

    app.goTo().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.goTo().gotoNewContact();
      app.getContactHelper().createContact(new ContactData("Надежда", "Ивановна", "Сидорова",
              "ул.Изюмская, д.1, кв.130", "+7(000)123-12-12", "+7(495)123-12-12", "222@mail.ru",
              "1980", "домофон 130", "Друзья"), true);
    }
  }

  @Test(enabled = true)

  public void ContactModification() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().selectContact(index);
    app.getContactHelper().initContactModification(index);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Надежда", "Ивановна", "Макарова",
            "ул.Изюмская, д.1, кв.130", "+7(000)123-12-12", "+7(495)123-12-12", "222@mail.ru",
            "1980", "домофон 130", "Друзья");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().updateContactForm();
    app.getContactHelper().returnHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    System.out.println("сравниваем до" + before);
    System.out.println("после " + after);

    Assert.assertEquals(before, after);

  }

}
