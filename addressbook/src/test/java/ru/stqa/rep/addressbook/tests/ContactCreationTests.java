package ru.stqa.rep.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.Contacts;
import ru.stqa.rep.addressbook.model.GroupData;
import ru.stqa.rep.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @DataProvider()
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File
            ("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
     XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
  }
}
  @DataProvider()
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File
            ("src/test/resources/contacts1.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")

  public void testContactCreation(ContactData contact) {
    Groups groups = app.db().groups(); //извлекаем список групп из БД
     File photo = new File("src/test/resources/Koala.jpg");

    // Извлекаем из БД множество контактов ДО
    Contacts before = app.db().contacts();

   // создаем контакт
    app.goTo().gotoHomePage();
    app.gotoNewContact();
    app.contact().create(contact, true);
    System.out.println("КОНТАКТ " + contact);
    System.out.println("Входит в в группы: " +contact.getGroups());

  // Извлекаем из БД множество контактов ПОСЛЕ
    Contacts after = app.db().contacts();
    System.out.println("БЫЛО: "+ before.size()+ " СТАЛО: " +after.size());

    // проверяем, что контактов стало больше на 1
    assertThat(after.size(), equalTo((before.size() + 1)));

      assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    // проверяем что в мнтерфейсе то же, что и в БД
    verifyContactListInUI();
  }

  @Test(enabled = false)   // тест проверки - существует ли файл с фото
  public void testCurrentDir() {
    File currentDir = new File(".");
    File photo = new File("src/test/resources/pic2.PNG");
    System.out.println(currentDir.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
