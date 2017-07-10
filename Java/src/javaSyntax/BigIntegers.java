package javaSyntax;

import java.math.BigInteger;

public class BigIntegers {

    static BigInteger factorial(int N)
    {
        // Initialize result
        BigInteger f = new BigInteger("1"); // Or BigInteger.ONE
        // Multiply f with 2, 3, ...N
        for (int i = 2; i <= N; i++)
            f = f.multiply(BigInteger.valueOf(i));
 
        return f;
    }
    static long f(int N)
    {
        // Initialize result
        long f = 1; // Or BigInteger.ONE
 
        // Multiply f with 2, 3, ...N
        for (int i = 2; i <= N; i++)
            f = f*i;
        return f;
    }
 
    // Driver method
    public static void main(String args[]) throws Exception
    {
        int N = 30;
        System.out.println(factorial(N));
    }

}
