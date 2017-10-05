package ru.stqa.rep.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.rep.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.IE));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

    @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
