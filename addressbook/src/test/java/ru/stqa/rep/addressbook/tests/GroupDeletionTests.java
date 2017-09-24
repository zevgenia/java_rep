package ru.stqa.rep.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;
import ru.stqa.rep.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if ((app.group().all().size() == 0)) {
      app.group().create(new GroupData().withName("Друзья").withHeader("Друзья").withFooter("Домашняя группа"));

    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size()-1);
    assertThat(after, equalTo(before.without(deletedGroup)));
  }
}
