package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if ((app.group().List().size() == 0)) {
      app.group().create(new GroupData("Друзья", "Друзья", "Домашняя группа"));
    }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupData> before = app.group().List();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = app.group().List();
    Assert.assertEquals(after.size(), index);
    System.out.println("Было: " + before.size() + " Стало: " + after.size());

    before.remove(index);
    Assert.assertEquals(before, after);

  }


}
