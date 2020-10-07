package hu.kincstar.javasetraining.homework;

import org.junit.Assert;
import org.junit.Test;

/**
 * Fibonacci szám tesztelése
 */
public class FibonacciTest {
    @Test
    public void IllegalAndLegal() {
        Assert.assertTrue(Fibonacci.isFibonacciNumber(3));
        Assert.assertFalse(Fibonacci.isFibonacciNumber(9));
    }
}
