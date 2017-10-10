package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;
import ru.stqa.rep.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) { //обращение к БД получение списка групп, вычисление кол-ва
      app.goTo().groupPage();
      //создание группы
      app.group().create(new GroupData().withName("Друзья").withHeader("Друзья").withFooter("Домашняя группа"));
    }
  }

  @Test(enabled = true)
  public void GroupModification() { //получение списка групп из БД до модификации
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId((modifiedGroup).getId())
            .withName("Семья").withHeader("Семья").withFooter("Родные");

    app.goTo().groupPage();
    app.group().modify(group);

    assertEquals(app.group().count(), before.size()); //ф-ция хэширования
    System.out.println("БЫЛО: "+ app.group().count()+ " СТАЛО: " +(before.size()));

    Groups after = app.db().groups(); //получение списка групп из БД после модификации

    assertEquals(after.size(), before.size());
    System.out.println("БЫЛО: "+ before.size()+ " СТАЛО: " +after.size());

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    System.out.println("СТАРАЯ ПРОВЕРКА ДО: "+ after+ " СТАЛО: " +before.without(modifiedGroup).withAdded(group));

    verifyGroupListInUI();


  }



}
