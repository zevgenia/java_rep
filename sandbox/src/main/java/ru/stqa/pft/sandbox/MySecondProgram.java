package ru.stqa.pft.sandbox;

public class MySecondProgram {

  public static void main(String[] args) {

    hello("world");
    hello("Alexei");
    hello("Дорогой друг Карлсон");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + area(s));

    Rectangle r = new Rectangle(6, 4);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));
  }

  public static void hello(String some) {

    System.out.println("Greeting, " + some + "!");
  }

  public static double area(Square s) {

    return s.l * s.l;
  }

  public static double area(Rectangle r) {
    return r.a * r.b;
  }
}


