package org.example;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String inputFile1 = "input1.txt";
        String inputFile2 = "input2.txt";
        String mergedFile = "merged.txt";
        String commonFile = "common.txt";

        try {
            // Reading integers from the first input file
            List<Integer> list1 = readIntegersFromFile(inputFile1);

            // Reading integers from the second input file
            List<Integer> list2 = readIntegersFromFile(inputFile2);

            // Merging the contents of the two input files
            List<Integer> mergedList = new ArrayList<>(list1);
            mergedList.addAll(list2);

            // Writing the merged contents to "merged.txt"
            writeIntegersToFile(mergedList, mergedFile);

            // Identifying the common integers in both lists
            Set<Integer> commonIntegers = new HashSet<>(list1);
            commonIntegers.retainAll(list2);

            // Writing the common integers to "common.txt"
            writeIntegersToFile(new ArrayList<>(commonIntegers), commonFile);

        } catch (FileNotFoundException e) {
            System.err.println("One of the input files was not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("An error occurred while reading or writing files: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("A non-integer value was encountered in the input files: " + e.getMessage());
        }
    }

    // Method to read integers from a file and return them as a list
    private static List<Integer> readIntegersFromFile(String filename) throws IOException {
        List<Integer> integers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                integers.add(Integer.parseInt(line));
            }
        }
        return integers;
    }

    // Method to write a list of integers to a file
    private static void writeIntegersToFile(List<Integer> integers, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : integers) {
                writer.write(String.valueOf(num));
                writer.newLine();
            }
        }
    }
}