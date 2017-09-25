package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase{

    @Test

    public void testContactPhones(){
        app.goTo().gotoHomePage();
        ContactData contact = app.Contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.Contact().InfoFromEditForm(contact);

    }

}
