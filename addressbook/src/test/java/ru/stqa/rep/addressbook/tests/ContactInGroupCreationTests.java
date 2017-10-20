package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.Contacts;
import ru.stqa.rep.addressbook.model.GroupData;
import ru.stqa.rep.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInGroupCreationTests extends TestBase {


  @BeforeMethod
  //если нет ни одной группы то надо какую-нибудь создать
  public void ensurePreconditionGroup() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Друзья").withHeader("Друзья").withFooter("Домашняя группа"));
    }
  }


  @Test
  public void testContactAddGroup() {

// Создаем контакт и добавляем его в какую в группу
    Groups groups = app.db().groups(); //извлекаем список групп из БД
        ContactData newContact = new ContactData().withFirstname("Надежда").withMiddlname("Ивановна").withLastname("Соколова")
                .withAddress("ул.Изюмская, д.1, кв.130").withMobile("+7(499)123-12-12")
                .withHome("+7(495)123-12-12").withWork("+7(495)555-55-55").withEmail("222@mail.ru")
                .withNote("домофон 130");

        app.goTo().gotoHomePage();
        app.goTo().gotoNewContact();
        app.contact().create(newContact, true);

    ContactData newContact = new inGroup(groups.iterator().next()).
            app.contact().modify();
//проверки добавления контакта
    Contacts before = app.db().contacts(); //кол-во контактов в БД ДО
    Groups beforeGroups  = newContact.getGroups(); //кол-во контактов в группе ДО
    app.goTo().gotoNewContact();
    app.contact().create(newContact, true);
    System.out.println("КОНТАКТ " + newContact);
    System.out.println("Входит в в группы: " +newContact.getGroups());
    Groups afterGroups  = newContact.getGroups(); //кол-во контактов в группе ПОСЛЕ
    Contacts after = app.db().contacts(); //кол-во контактов в БД

    System.out.println("БЫЛО: " + before.size() + " СТАЛО: " + after.size());
    System.out.println("Контакт был включен в  " + beforeGroups.size() + " групп" );
    System.out.println("Сейчас контакт  включен в " + afterGroups.size() + " групп" );

    // проверяем, что контактов стало больше на 1
    assertThat(after.size(), equalTo((before.size()+1)));

//    assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

// проверяем что в интерфейсе то же, что и в БД
    verifyContactListInUI();
  }
 }
