package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {


  @BeforeMethod

  public void endsurePrecondition() {
//    app.goTo().groupPage();
    app.goTo().gotoHomePage();
    if (app.contact().all().size() == 0) {
      app.goTo().gotoNewContact();
      app.contact().create(new ContactData().withFirstname("Надежда").withMiddlname("Ивановна")
              .withLastname("Сидорова").withAddress("ул.Изюмская, д.1, кв.130")
              .withMobile("+7(499)123-12-12").withHome("+7(495)123-12-12").withWork("+7(495)555-55-55")
              .withEmail("222@mail.ru").withYear("1980").withNote("домофон 130"), true);
    }
  }

  @Test

  public void testContactPhones() {
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next(); //загружаем множество контактов с гл.страницы и выбираем контакт случ.образом
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact); //загружает иформацию из формы со страницы редактирования

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    System.out.println("сравниваем " + contact.getAllEmails() + " и " + mergeEmails(contactInfoFromEditForm));
  }

  private String mergeEmails(ContactData contact) {

    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }
}
