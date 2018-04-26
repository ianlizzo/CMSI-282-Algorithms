public class Fibonacci {

    private static long fibonacciIterator(int n) {
        if (n == 0) { return 0; }
        long a = 0, b = 1, tempSum = 0;
        for (int i = 2; i <= n; i++) {
            tempSum = a + b;
            a = b;
            b = tempSum;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(fibonacciIterator(10));
    }


}
