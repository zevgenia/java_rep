package ru.stqa.rep.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition (){
    app.goTo().groupPage();
    if (app.group().List().size() == 0) {
      app.group().create(new GroupData("Друзья", "Друзья", "Домашняя группа"));
    }
  }

  @Test
  public void GroupModification() {
    List<GroupData> before = app.group().List();
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(index).getId(), "Друзья", null, null);
    app.group().modify(index, group);
    List<GroupData> after = app.group().List();

    Assert.assertEquals(after.size(), before.size());
    System.out.println("Было: " + before.size() + " Стало: " + after.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }



}
