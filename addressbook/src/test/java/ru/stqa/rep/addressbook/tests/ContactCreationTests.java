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
            .withMobile("+7(499)123-12-12").withHome("+7(495)123-12-12").withWork("+7(495)555-55-55")
            .withEmail("222@mail.ru").withYear("1980").withNote("домофон 130").withGroup("Друзья");
    app.Contact().create(contact, true);
    assertThat(app.Contact().count(), equalTo(before.size() + 1));
    Contacts after = app.Contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
