package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

    @Test

    public void GroupModification () {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification ();
        app.getGroupHelper().fillGroupForm(new GroupData("Друзья", "Друзья", "Домашняя группа"));
        app.getGroupHelper().submitGroupModification ();
        app.getGroupHelper().returnToGroupPage();
    }

}
