
//@author: Ian Lizarda
//@date: 02/22/2018

import java.util.Arrays;
public class DominoSolverTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        runTests();
    }

    public static boolean runTests () {
        attempts = 0;
        successes = 0;

        test_isValid();
        test_shrinkArray();
        test_maxCost();

        System.out.println(successes + "/" + attempts + " tests passed.");
        return successes == attempts;
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    private static void test_isValid() {
        System.out.println("Testing isValid...");

        try {
            int[] test = {5, 10, 10, 7, 7, 6};
            displaySuccessIfTrue(DominoSolver.isValid(test));
        } catch (Exception exc) {
            displayFailure();
        }

        try {
            int[] test = {5, -10, 10, 7, 7, 6};
            displaySuccessIfTrue(!DominoSolver.isValid(test));
        } catch (Exception exc) {
            displayFailure();
        }

        try {
            String[] test = {"a", "10", "10", "10", "7", "6"};
            DominoSolver.main(test);
            displayFailure();
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        }

    }


    private static void test_shrinkArray() {
        System.out.println("Testing shrinkArray...");

        try {
            int[] test = {0, 0, 0, 0};
            int[] answer = {0, 0, 0};
            displaySuccessIfTrue(Arrays.equals(DominoSolver.shrinkArray(test),
                answer));
        } catch (Exception exc) {
            displayFailure();
        }

        try {
            int[] test = {0, 0, 1, 0};
            int[] shrinkArray = DominoSolver.shrinkArray(test);
            displayFailure();
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        }

        try {
            int[] test = {0, 1, -0, 0};
            int[] shrinkArray = DominoSolver.shrinkArray(test);
            displayFailure();
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        }
    }

    private static int dominoSolution(int[] set) {
        int n = set.length;
        int[][] memo = new int[n][n];
        return DominoSolver.maxCost(memo, set, 1, n - 1);
    }

    private static void test_maxCost() {
        System.out.println("Testing maxCost...");

        try {
            int[] test = {2, 5, 5, 9, 9, 6, 6, 4};
            test = DominoSolver.shrinkArray(test);
            displaySuccessIfTrue(dominoSolution(test) == 436);
        } catch (Exception exc) {
            displayFailure();
        }

        try {
            int[] test = {10, 8, 8, 15, 15, 12, 12, 6};
            test = DominoSolver.shrinkArray(test);
            displaySuccessIfTrue(dominoSolution(test) == 3720);
        } catch (Exception exc) {
            displayFailure();
        }
    }
}
