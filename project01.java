package gr.aueb.cf.projects;

import java.io.*;
import java.util.*;

public class project01 {
    public static void main(String[] args) throws IOException {
        List<Integer> numbers = readNumbersFromFile("input.txt");
        Collections.sort(numbers);
        List<List<Integer>> combinations = generateCombinations(numbers, 6);
        List<List<Integer>> validCombinations = filterCombinations(combinations);
        writeCombinationsToFile(validCombinations, "output.txt");
    }

    public static List<Integer> readNumbersFromFile(String filename) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (String token : tokens) {
                    int num = Integer.parseInt(token);
                    if (num >= 1 && num <= 49) {
                        numbers.add(num);
                    }
                }
            }
        }
        return numbers;
    }

    public static List<List<Integer>> generateCombinations(List<Integer> numbers, int r) {
        List<List<Integer>> combinations = new ArrayList<>();
        generateCombinationsHelper(numbers, new ArrayList<>(), 0, r, combinations);
        return combinations;
    }

    private static void generateCombinationsHelper(List<Integer> numbers, List<Integer> temp, int start, int r, List<List<Integer>> combinations) {
        if (temp.size() == r) {
            combinations.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < numbers.size(); i++) {
            temp.add(numbers.get(i));
            generateCombinationsHelper(numbers, temp, i + 1, r, combinations);
            temp.remove(temp.size() - 1);
        }
    }

    public static List<List<Integer>> filterCombinations(List<List<Integer>> combinations) {
        List<List<Integer>> validCombinations = new ArrayList<>();
        for (List<Integer> combination : combinations) {
            if (isValidCombination(combination)) {
                validCombinations.add(combination);
            }
        }
        return validCombinations;
    }

    private static boolean isValidCombination(List<Integer> combination) {
        return countEvenNumbers(combination) <= 4 &&
                countOddNumbers(combination) <= 4 &&
                countConsecutiveNumbers(combination) <= 2 &&
                countSameEndingNumbers(combination) <= 3 &&
                countSameTens(combination) <= 3;
    }

    private static int countEvenNumbers(List<Integer> combination) {
        int count = 0;
        for (int num : combination) {
            if (num % 2 == 0) count++;
        }
        return count;
    }

    private static int countOddNumbers(List<Integer> combination) {
        return combination.size() - countEvenNumbers(combination);
    }

    private static int countConsecutiveNumbers(List<Integer> combination) {
        int count = 0;
        for (int i = 1; i < combination.size(); i++) {
            if (combination.get(i) == combination.get(i - 1) + 1) {
                count++;
            }
        }
        return count;
    }

    private static int countSameEndingNumbers(List<Integer> combination) {
        int[] endings = new int[10];
        for (int num : combination) {
            endings[num % 10]++;
        }
        int maxEnding = 0;
        for (int count : endings) {
            maxEnding = Math.max(maxEnding, count);
        }
        return maxEnding;
    }

    private static int countSameTens(List<Integer> combination) {
        int[] tens = new int[5];
        for (int num : combination) {
            tens[num / 10]++;
        }
        int maxTens = 0;
        for (int count : tens) {
            maxTens = Math.max(maxTens, count);
        }
        return maxTens;
    }

    public static void writeCombinationsToFile(List<List<Integer>> combinations, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (List<Integer> combination : combinations) {
                bw.write(combination.toString());
                bw.newLine();
            }
        }
    }
}
