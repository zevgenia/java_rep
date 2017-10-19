package ru.stqa.rep.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.ContactData;
import ru.stqa.rep.addressbook.model.Contacts;
import ru.stqa.rep.addressbook.model.GroupData;
import java.util.List;

public class HbConnectionTest {
  Logger logger= LoggerFactory.getLogger(HbConnectionTest.class);

  private SessionFactory sessionFactory;

  @BeforeClass

  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }


  @Test
  public void testHbConnectionGroupsTest() {

    logger.info("Start test HbConnectionTest");

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData where deprecated = '0000-00-00'").list();

    session.getTransaction().commit();
    session.close();
    logger.info("Stop test HbConnectionTest");

    for (GroupData group : result) {
      System.out.println("ГРУППА "+ group);
      System.out.println("Содержит контакты: "+ group.getContacts());
    }
  }


  @Test (enabled = false)
  public void testHbConnectionContactsTest() {

    logger.info("Start test HbConnectionTest");

    Session session = sessionFactory.openSession(); //инициализация соединения
    session.beginTransaction();
    //извлекается информация о контакте из БД
    List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();

    session.getTransaction().commit();
    session.close();
    logger.info("Stop test HbConnectionTest");

    for (ContactData contact : result) {
      System.out.println("КОНТАКТ " + contact);
      System.out.println("Входит в в группы: " +contact.getGroups());
    }
  }
}
