package gr.aueb.cf.projects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class project03 {
    public static void main(String[] args) throws IOException {
        int[][] frequency = new int[128][2];
        readCharactersFromFile("input.txt", frequency);
        printCharacterStatistics(frequency);
    }

    public static void readCharactersFromFile(String filename, int[][] frequency) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int c;
            while ((c = br.read()) != -1) {
                if (c >= 32 && c < 128) {
                    frequency[c][0] = c;
                    frequency[c][1]++;
                }
            }
        }
    }

    public static void printCharacterStatistics(int[][] frequency) {
        System.out.println("Character statistics sorted by character:");
        Arrays.sort(frequency, Comparator.comparingInt(a -> a[0]));
        for (int[] entry : frequency) {
            if (entry[1] > 0) {
                System.out.printf("%c: %d times\n", entry[0], entry[1]);
            }
        }

        System.out.println("\nCharacter statistics sorted by frequency:");
        Arrays.sort(frequency, (a, b) -> Integer.compare(b[1], a[1]));
        for (int[] entry : frequency) {
            if (entry[1] > 0) {
                System.out.printf("%c: %d times\n", entry[0], entry[1]);
            }
        }
    }
}
