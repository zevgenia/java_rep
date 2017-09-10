package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

  @Test
  public void testPrim(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test (enabled = false)
  public void testNotPrime(){
      Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }
}


