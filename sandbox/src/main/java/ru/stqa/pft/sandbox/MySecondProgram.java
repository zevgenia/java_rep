package ru.stqa.pft.sandbox;

import java.text.NumberFormat;
import java.util.Formatter;

public class MySecondProgram {

  public static void main(String[] args) {

    Point p1 = new Point(5, 2);
    Point p2 = new Point(10, 12);

    NumberFormat formatter = NumberFormat.getNumberInstance();
    String s = formatter.format(p2.distance(p1));

    System.out.println("Расстояние между двумя точками с координатами " + "А(" + p1.x + ";" + p1.y + ") и В(" + p2.x +
            ";" + p2.y + ") = " + s);

//
//    System.out.println(s);

//    NumberFormat formatter = NumberFormat.getNumberInstance();
//    formatter.setMaximumFractionDigits(2);
//    formatter.setMaximumIntegerDigits (6);
//    String r = formatter.format (p2.distance( p1));
//    System.out.println(r);

  }

}


