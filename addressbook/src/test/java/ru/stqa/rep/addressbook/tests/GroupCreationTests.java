package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;
import ru.stqa.rep.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider ()
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{"Друзья'", "Друзья", "Домашняя группа"});
    list.add(new Object[]{"Семья", "Семья", "Родные"});
    list.add(new Object[]{"Работа", "Работа", "Сослуживцы"});
    return list.iterator();

  }

  @Test (dataProvider = "validGroups")

  public void testGroupCreation(String name, String header, String footer) {
      GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
      app.goTo().groupPage();
      Groups before = app.group().all();

      app.group().create(group);
      assertThat(app.group().count(),equalTo(before.size() + 1));
      Groups after = app.group().all();
      System.out.println("Было: " + before.size() + " Стало: " + after.size());

      assertThat(after, equalTo(
              before.withAdded(group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
    }


  @Test(enabled = false)

  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Друзья'").withHeader("Друзья").withFooter("Домашняя группа");
    app.group().create(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after = app.group().all();
    System.out.println("Было: " + before.size() + " Стало: " + after.size());

    assertThat(after, equalTo(before));
  }

}
