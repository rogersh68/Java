// Assignment W11
// Hannah Rogers
// CIT-260 Section 5
// March 19, 2020

package Assignments;

// import scanner
import java.util.Scanner;

// import io
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // declare constant values
        final int SIZE = 10;

        // Tell the user what the program does
        System.out.println("This program gets ten numbers from the user, and");
        System.out.println("computes and displays the average");

        // Prompt for 10 integers
        System.out.println();
        System.out.println("Please enter ten integer values.");

        // Create Scanner object
        Scanner input = new Scanner(System.in);

        // initialize array
        int[] integers = new int[SIZE];

        // add 10 integers to array
        for (int i = 0; i < SIZE; i++) {
            boolean valid = false;
            do {
                try {
                    System.out.format("Enter integer %d:%n", i + 1);
                    integers[i] = input.nextInt();
                    valid = true;
                }
                // send error message if input is not an integer
                catch (java.util.InputMismatchException e) {
                    System.out.println("Error: input must be an integer.");
                    // clear the input
                    input.nextLine();
                }
            } while (!valid);
        }

        // create a file named data.txt
        File data = new File("data.txt");

        // open the file
        try {
            PrintWriter output = new PrintWriter(data);

            // write data to a file
            for (int i = 0; i < integers.length; i++) {
                output.println(integers[i]);
            }

            // close the file
            output.close();
        }
        // exit the program if file cannot be opened
        catch (IOException e) {
            System.out.println("Could not open the file");
            System.exit(1);
        }

        // initialize sum
        double sum = 0;

        // read file
        try {
            Scanner fileRdr = new Scanner(data);
            for (int i = 0; i < SIZE; i++) {
                // compute sum of integers
                int integer = fileRdr.nextInt();
                sum += integer;
            }
        }
        // exit the program if the file cannot be read
        catch(IOException e) {
            System.out.println("Could not read the file");
            System.exit(1);
        }

        // compute and display the average
        double average = sum / SIZE;
        System.out.format("The average of the input values is %.2f%n", average);

        // Display a goodbye message
        System.out.println("Goodbye...");
    }
}

