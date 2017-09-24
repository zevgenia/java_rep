package ru.stqa.rep.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;
import ru.stqa.rep.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition (){
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("Друзья").withHeader("Друзья").withFooter("Домашняя группа"));
    }
  }

  @Test (enabled = true)
  public void GroupModification() {
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData().withId((modifiedGroup).getId())
            .withName("Семья").withHeader("Семья").withFooter("Родные");
    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);
    assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));



  }



}
