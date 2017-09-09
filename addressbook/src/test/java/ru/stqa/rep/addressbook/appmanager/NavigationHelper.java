package ru.stqa.rep.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoNewContact() {
        click(By.linkText("add new"));
    }

    public void gotoHomePage() {
//        click(By.linkText("home"));
        click(By.xpath("//div[@id='nav']//a[.='home']"));

    }
}
