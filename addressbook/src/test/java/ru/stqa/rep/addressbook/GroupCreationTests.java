package ru.stqa.rep.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Друзья", "Друзья", "Домашняя группа"));
    submitFormCreation();
    returnToGroupPage();
  }

}
