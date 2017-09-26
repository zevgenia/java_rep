package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

    @BeforeMethod

    public void endsurePrecondition() {
//    app.goTo().groupPage();
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0) {
            app.goTo().gotoNewContact();
            app.contact().create(new ContactData().withFirstname("Надежда").withMiddlname("Ивановна")
                    .withLastname("Сидорова").withAddress("ул.Изюмская, д.1, кв.130")
                    .withMobile("+7(499)123-12-12").withHome("+7(495)123-12-12").withWork("+7(495)555-55-55")
                    .withEmail("222@mail.ru").withYear("1980").withNote("домофон 130").withGroup("Друзья"), true);
        }
    }
    private ContactData contactInfoFromEditForm;
    private ContactData contact;

    @Test

    public void testContactPhones(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next(); //загружаем множество контактов с гл.страницы и выбираем контакт случ.образом
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact); //загружает иформацию из формы со страницы редактирования

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        System.out.println("сравниваем "+ contact.getAllPhones()+" и "+ mergePhones(contactInfoFromEditForm));
    }

    private String mergePhones(ContactData contact) {

       return Arrays.asList(contact.getHome(),contact.getMobile(),contact.getWork())
               .stream().filter((s) -> !s.equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned (String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", ""); //очищает от лишних символов
    }
}
