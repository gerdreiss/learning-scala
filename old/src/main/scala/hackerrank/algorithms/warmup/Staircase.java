package hackerrank.algorithms.warmup;

import java.util.Scanner;

import static java.util.Collections.nCopies;

public class Staircase {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            nCopies(n - i, " ").forEach(System.out::print);
            nCopies(i, "#").forEach(System.out::print);
            System.out.println();
        }
    }
}
