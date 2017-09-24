package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;
import ru.stqa.rep.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test(enabled = false)

  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Друзья").withHeader("Друзья").withFooter("Домашняя группа");
    app.group().create(group);
    assertThat(app.group().count(),equalTo(before.size() + 1));
    Groups after = app.group().all();
    System.out.println("Было: " + before.size() + " Стало: " + after.size());

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
  }

  @Test

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
