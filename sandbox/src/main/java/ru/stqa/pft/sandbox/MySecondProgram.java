package ru.stqa.pft.sandbox;

import java.text.NumberFormat;
import java.util.Formatter;

public class MySecondProgram {

  // public static void main(String[] args) {
//
//  hello("world");
//  hello("Alexei");
//hello("Дорогой друг Карлсон");

//    Square s = new Square(5);
//   System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

//    Rectangle r = new Rectangle(6, 4);
//    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
//  }

//  public static void hello(String some) {

//    System.out.println("Greeting, " + some + "!");
//}

  public static void main(String[] args) {

    Point p1 = new Point(5, 2);
    Point p2 = new Point(10, 12);
    System.out.println("Расстояние между двумя точками с координатами " + "А(" + p1.x + ";" + p1.y + ") и В(" + p2.x +
            ";" + p2.y + ") = " + p2.distance( p1));

//    NumberFormat formatter = NumberFormat.getNumberInstance();
//    String s = formatter.format(p2.distance(p1));
//    System.out.println(s);

//    NumberFormat formatter = NumberFormat.getNumberInstance();
//    formatter.setMaximumFractionDigits(2);
//    formatter.setMaximumIntegerDigits (6);
//    String r = formatter.format (p2.distance( p1));
//    System.out.println(r);

  }

}


