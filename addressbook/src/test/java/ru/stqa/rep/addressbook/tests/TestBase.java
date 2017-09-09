package ru.stqa.rep.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.rep.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

    @AfterMethod
  public void tearDown() {
    app.stop();
  }


}
