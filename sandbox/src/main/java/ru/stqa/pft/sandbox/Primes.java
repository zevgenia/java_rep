package ru.stqa.pft.sandbox;

public class Primes {

  public static boolean isPrime (int n){

    for (int i = 2; i< n; i++){
      if (n % i == 0) { //делится без остатка
        return false;  //есть делитель
      }
    }
    return true; //нет делителя
  }
}
