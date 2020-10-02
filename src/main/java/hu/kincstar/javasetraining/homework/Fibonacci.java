package hu.kincstar.javasetraining.homework;

/**
 * Fibonacci szám kezelésére alkalmas osztály
 */
public class Fibonacci {
    /**
     * Eldönti a megadott értékről, hogy Fibonacci szám-e
     * @param f vizsgálandó érték
     * @return Fibonacci szám-e?
     */
    public static boolean isFibonacciNumber(int f)
    {
        if (f < 0) return false;
        if (f == 0) return true;
        int a = 0;
        int b = 1;
        int c;

        // 0, 1, 1, 2, 3, 5, 8, 13
        while (a + b <= f)
        {
            c = a;
            a = b;
            b = c + b;
            if (b == f)
                return  true;
        }

        return false;
    }

}
