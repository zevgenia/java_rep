package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("Друзья", "Друзья", "Домашняя группа"));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);
    System.out.println("Было: "+before+ " Стало: "+after);
  }

}
