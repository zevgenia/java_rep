package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance () {

    Point p1 = new Point (5,2);
    Point p2 = new Point (10,12);

        Assert.assertEquals(p2.distance (p1), 11.18);


  }
}
