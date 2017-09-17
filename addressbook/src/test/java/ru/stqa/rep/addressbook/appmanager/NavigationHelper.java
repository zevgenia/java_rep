package ru.stqa.rep.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {

    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }

  public void gotoNewContact() {
    click(By.linkText("add new"));
  }

  public void gotoHomePage() {
  click(By.xpath("//div/div[3]/ul/li[1]/a"));
//  click(By.xpath("//div[@id='nav']//a[.='home']"));
//  click(By.name("home"));
  }
}
