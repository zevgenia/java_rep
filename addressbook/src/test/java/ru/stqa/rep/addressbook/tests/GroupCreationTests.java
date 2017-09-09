package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("Друзья", "Друзья", "Домашняя группа"));
  }
}
