package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;

  }

  public  double distance(Point p1) {

    double d = (this.x - p1.x) * (this.x - p1.x) + (this.y - p1.y) * (this.y - p1.y);
    return Math.sqrt(d);
  }
}
