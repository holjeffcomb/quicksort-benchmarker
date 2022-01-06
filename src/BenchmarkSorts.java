/*
Author: Jeffrey Holcomb
Date: 9/13/2020
Course: CMSC 451
Title: Benchmark Quick Sort Algorithm
 */

// This class generates randomized data to store in the arrays that are sent to the Quick Sort algorithms.

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class BenchmarkSorts {

    public static void main(String[] args) throws UnsortedException {

        Random rand = new Random();

        // Enter warm-up phase
        warmUp();

        try {
            BufferedWriter bwRecursive = new BufferedWriter(new FileWriter("RecursiveOutput.txt"));
            BufferedWriter bwIterative = new BufferedWriter(new FileWriter("IterativeOutput.txt"));

            int[] arr, arrCopy;

            int n = 50;

            for (int size = 100; size <= 1000 ; size += 100) { // for loop for each data set size, starting at 100 and incrementing by 100 each loop until 1000

                arr = new int[size];
                arrCopy = new int[size]; // copy to send to iterative sort

                MyQuickSort mqs; // Quick Sort object

                // Write size as first entry to each row
                bwRecursive.write(size + " ");
                bwIterative.write(size + " ");

                // For loop to randomly generate data for each entry
                for (int i = 0; i < n; i++) {

                    mqs = new MyQuickSort();

                    // Populate array with random ints
                    for (int j = 0; j < arr.length; j++) {
                        arr[j] = rand.nextInt(100);
                    }

                    // Make copy of array for iterative sort
                    System.arraycopy(arr, 0, arrCopy, 0, arr.length);

                    // Print unsorted array
                    System.out.println("Run # " + (i+1) + ", Set Size: " + size);
                    System.out.println("Unsorted Array:");
                    printArrayFirstTen(arr);

                    mqs.resetCount(); // Reset count

                    // Perform and time recursive quick sort
                    long start = System.nanoTime();
                    mqs.recursiveSort(arr, 0, arr.length - 1);
                    long finish = System.nanoTime();
                    mqs.setTime(finish - start);

                    if (!isSorted(arr)) throw new UnsortedException("Error! Unsorted list found!"); // Check if result is sorted

                    bwRecursive.write(mqs.getCount() + "," + mqs.getTime() + " "); // Write result to buffered writer

                    System.out.println("Sorted Array (Recursive): ");
                    printArrayFirstTen(arr);

                    mqs.resetCount(); // Reset count

                    // Perform and time iterative quick sort
                    start = System.nanoTime();
                    mqs.iterativeSort(arrCopy, 0, arrCopy.length - 1);
                    finish = System.nanoTime();
                    mqs.setTime(finish - start);

                    if (!isSorted(arrCopy)) throw new UnsortedException("Error! Unsorted list found!"); // Check if result is sorted

                    bwIterative.write(mqs.getCount() + "," + mqs.getTime() + " "); // Write result to buffered writer

                    System.out.println("Sorted Array (Iterative):");
                    printArrayFirstTen(arrCopy);
                    System.out.println("-----");
                }
                bwRecursive.newLine();
                bwIterative.newLine();
            }
            bwRecursive.close();
            bwIterative.close();
            System.out.println("Buffered Writers closed");
        } catch (IOException io) {
            System.out.println("File Not Found!");
        }
    }

    // Warm up method
    private static void warmUp(){
        System.out.println("Starting Warm up....");
        for(int i = 0; i < 100000; i++){
            WarmUpDummy dummy = new WarmUpDummy();
            dummy.m();
        }
        System.out.println("Ending Warm up.\n");
    }

    // Print first 10 elements in array
    private static void printArrayFirstTen(int[] arr) {
        int n;
        if (arr.length > 10) n = 10;
        else n = arr.length;

        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Check if array is sorted
    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }
}