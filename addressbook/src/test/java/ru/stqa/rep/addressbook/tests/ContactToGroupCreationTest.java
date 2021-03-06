package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.Contacts;
import ru.stqa.rep.addressbook.model.GroupData;
import ru.stqa.rep.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupCreationTest extends TestBase {


  @BeforeMethod

//если нет ни одной группы то надо какую-нибудь создать
  public void ensurePrecondition() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Друзья").withHeader("Друзья").withFooter("Домашняя группа"));
    }
  }

  @Test
  public void testContactToGroupCreation() {

//создаем новый контакт и присваиваем ему какую-нибудь случайную группу
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData newContact = new ContactData().withFirstname("Надежда").withMiddlname("Ивановна").withLastname("Соколова")
            .withAddress("ул.Изюмская, д.1, кв.130").withMobile("+7(499)123-12-12")
            .withHome("+7(495)123-12-12").withWork("+7(495)555-55-55").withEmail("222@mail.ru")
            .inGroup(groups.iterator().next()).withNote("домофон 130");

    app.goTo().gotoNewContact();
    app.contact().create(newContact, true);

    Contacts after = app.db().contacts();
    System.out.println("БЫЛО: " + before.size() + " СТАЛО: " + after.size());

// проверяем, что контактов стало больше на 1
    assertThat(after.size(), equalTo((before.size() + 1)));

    System.out.println("СРАВНИВАЕМ " + after + "\n" + before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())));

// проверяем списки контактов ДО и ПОСЛЕ извлеченные из БД
    assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));


  }
}
