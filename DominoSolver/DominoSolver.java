
//@author: Ian Lizarda
//@date: 02/22/2018

public class DominoSolver {

    public static boolean isValid(int[] set) {
        if (set.length < 4 || set.length % 2 != 0 || set[0] < 0) {
            return false;
        }
        for (int i = 1; i < set.length - 1; i+=2) {
            if (set[i] != set[i + 1] || set[i] < 0 || set[i + 2] < 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] shrinkArray(int[] set) {
        if (!isValid(set)) {
            throw new IllegalArgumentException("Domino input is not valid!");
        }
        int[] subset = new int[(set.length + 2) / 2];
        subset[0] = set[0];
        subset[subset.length - 1] = set[set.length - 1];
        for (int i = 1; i < subset.length - 1; i++) {
            subset[i] = set[i * 2];
        }
        return subset;
    }

    public static int maxCost(int[][] memo, int[] set, int i, int j) {
        if (memo[i][j] > 0) {
            return memo[i][j];
        }
        if (i == j) {
            memo[i][j] = 0;
        }
        for (int k = i; k < j; k++) {
            int max = maxCost(memo, set, i, k) + maxCost(memo, set, k + 1, j)
                + set[i - 1] * set[k] * set[j];
            if (max > memo[i][j]) {
                memo[i][j] = max;
            }
        }
        return memo[i][j];
    }

    public static void main(String args[]){
        int[] domino = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            try {
                domino[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Input integers only!");
            }
        }
        int[] set = shrinkArray(domino);
        int n = set.length;
        int[][] memo = new int[n][n];
        System.out.println(maxCost(memo, set, 1, n - 1));
    }
}
