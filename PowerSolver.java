
public class PowerSolver {

    private static long power(long x, long n) {
        if (n == 0) { return 1; }
        long halfPower = power(x, n / 2) * power(x, n / 2);
        return (n % 2 == 0) ? halfPower : x * halfPower;
    }

    private static long polynomial(long[] poly, long x) {
        long solution = poly[0];
        for(int i = 1; i < poly.length; i++) {
            solution = solution * x + poly[i];
        }
        return solution;
    }

    private static long gcd(long x, long y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    private static long lcm(long x, long y) {
        return (x * y) / gcd(x, y);
    }

    public static void main(String[] args) {
        System.out.println(power(12, 2));
        long[] poly = {2, 3, 4, 5, 1};
        System.out.println(polynomial(poly, 2));
        System.out.println(gcd(112, 48));
        System.out.println(lcm(15, 24));
    }
}
