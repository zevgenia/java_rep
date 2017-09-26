package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

    private ContactData contactInfoFromEditForm;
    private ContactData contact;

    @Test

    public void testContactPhones(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
        assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
    }


    private String mergePhones(ContactData contact) {
        String result = "";
        if(contact.getHome() != null ){
            result = result + contact + contact.getHome();

        }

        return result;

    }


    public String cleaned (String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

    }

}
