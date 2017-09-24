package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test(enabled = true)


  public void testContactCreation() {
//    app.goTo().groupPage();
    app.goTo().gotoHomePage();
    Contacts before = app.Contact().all();
    app.gotoNewContact();
    ContactData contact = new ContactData().withFirstname("Надежда").withMiddlname("Ивановна")
            .withLastname("Сидорова").withAddress("ул.Изюмская, д.1, кв.130")
            .withMobile("+7(000)123-12-12").withHome("+7(495)123-12-12")
            .withEmail("222@mail.ru").withYear("1980").withNote("домофон 130").withGroup("Друзья");
    app.Contact().create(contact, true);

    Contacts after = app.Contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
