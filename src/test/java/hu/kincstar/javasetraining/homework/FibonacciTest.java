package hu.kincstar.javasetraining.homework;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {
    @Test
    public void TestFibonacci() {
        Assert.assertTrue(Fibonacci.isFibonacciNumber(3));
        Assert.assertFalse(Fibonacci.isFibonacciNumber(9));
    }
}