package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test

    public void  ContactModification (){

        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification ();
        app.getContactHelper().fillContactForm(new ContactData("Надежда", "Ивановна", "Сидорова", "ул.Изюмская, д.1, кв.130", "+7(000)123-12-12", "+7(495)123-12-12", "222@mail.ru", "1980", "домофон 130"));
        app.getContactHelper().updateContactForm();
        app.getContactHelper().returnHomePage();

    }

}
