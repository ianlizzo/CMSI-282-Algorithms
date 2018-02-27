
import java.math.*;

public class RootFinder {
    private static double root (double x, double epsilon, int power) {
        if (x < 0) { return -1; }

        if (x == 1) { return 1; }

        double result;
        double negRange = round(x - (x * epsilon), 3);
        double posRange = round(x + (x * epsilon), 3);


        if (x > 0 && x < 1) {
            result = (x + 1) / 2;
        } else {
            result = x / 2;
        }
        
        while (round(Math.pow(result, power), 3) < negRange || round(Math.pow(result, power), 3) > posRange) {
            if (round(Math.pow(result, power), 3) > x) {
                result /= 2;
            } else if (round(Math.pow(result, power), 3) < x) {
                result += (result / 2);
            }
        }
        return round(result, 3);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(root(12, .001, 2));
        System.out.println(root(.5, .001, 2));
        System.out.println(root(.712, .001, 2));
        System.out.println(root(12, .001, 3));
        System.out.println(root(.125, .001, 3));
        System.out.println(root(-1, .001, 3));
        System.out.println(root(1, .001, 3));
        System.out.println(root(0, .001, 3));
    }
}
