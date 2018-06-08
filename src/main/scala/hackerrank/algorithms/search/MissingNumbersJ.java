package hackerrank.algorithms.search;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class MissingNumbersJ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Map.Entry<Integer, Long>> A = readAndCountNumbers(sc);
        readAndCountNumbers(sc).stream()
                .filter(e -> !A.contains(e))
                .map(Map.Entry::getKey).sorted()
                .map(String::valueOf)
                .reduce((i1, i2) -> i1 + " " + i2)
                .ifPresent(System.out::print);
    }

    private static Set<Map.Entry<Integer, Long>> readAndCountNumbers(Scanner sc) {
        return IntStream.range(0, sc.nextInt())
                .map(i -> sc.nextInt()).boxed()
                .collect(groupingBy(identity(), counting()))
                .entrySet();
    }
}
