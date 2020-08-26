
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;
import java.io.*;

public class RedBlackTreeTests {
    static Double doubleArray[];
    static String stringArray[];
    static int selectedType;
    static {
        initializeTestData();
    }

    public static void initializeTestData() {
        int selectType;
        int noOfElements;
        System.out.println("=========Init test data===========");
        try {
            System.out.println("Enter the type of data you want to use: \n 1. For String\n 2. For Double");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String value = reader.readLine();
            selectType = Integer.parseInt(value);
            // Set the selectedType global variable to use it later
            selectedType = selectType;
            System.out.println("Enter the number of elements you want to add:");
            value = reader.readLine();
            noOfElements = Integer.parseInt(value);
            
            if (selectType == 1) {
                stringArray = new String[noOfElements];
                for (int i = 0; i < noOfElements; i++) {
                    System.out.print((i+1) + ". ");
                    value = reader.readLine();
                    stringArray[i] = value;
                }

            } else if (selectType == 2) {
                doubleArray = new Double[noOfElements];
                for (int i = 0; i < noOfElements; i++) {
                    System.out.print((i+1) + ". ");
                    value = reader.readLine();
                    Double item = Double.parseDouble(value);
                    doubleArray[i] = item;
                }

            }
        } catch (IOException ie) {
            System.out.println("An error occured could not read input");
            ie.printStackTrace();
        }
    }

    @Test
    public void testRedBlackTreeAdd() {
        System.out.println("=========Testing add function===========");
        if (selectedType == 1) {
            RedBlackTreeExercise<String> rb = new RedBlackTreeExercise<>();
            String array[] = stringArray;
            // use A bids as test data
            for (int i = 0; i < array.length; i++) {
                // add to the red black tree
                rb.add(array[i]);
            }
            // Add an element

            // now search and check if all items have been inserted
            for (int i = 0; i < array.length; i++) {
                RedBlackTreeExercise.Node find = rb.search(array[i]);
                assertEquals(find.data, array[i]);
            }
        } else if (selectedType == 2) {
            RedBlackTreeExercise<Double> rb = new RedBlackTreeExercise<>();
            Double array[] = doubleArray;
            // use A bids as test data
            for (int i = 0; i < array.length; i++) {
                // add to the red black tree
                rb.add(array[i]);
            }
            // Add an element

            // now search and check if all items have been inserted
            for (int i = 0; i < array.length; i++) {
                RedBlackTreeExercise.Node find = rb.search(array[i]);
                assertEquals(find.data, array[i]);
            }
        }

    }

    // Test if the size function returns the true size
    @Test
    public void testRedBlackTreeSize() {
        System.out.println("=========Testing size function===========");
        if (selectedType == 1) {
            RedBlackTreeExercise<String> rb = new RedBlackTreeExercise<>();
            String array[] = stringArray;
            for (String bid : array) {
                // add to the red black tree
                rb.add(bid);
            }

            for (String bid : array) {
                // add to the red black tree
                rb.add(bid);
            }
            assertTrue("Size must be greater than zero after adding elements ", rb.size() == array.length);
        } else if (selectedType == 2) {
            RedBlackTreeExercise<Double> rb = new RedBlackTreeExercise<>();
            Double array[] = doubleArray;
            for (Double bid : array) {
                // add to the red black tree
                rb.add(bid);
            }

            for (Double bid : array) {
                // add to the red black tree
                rb.add(bid);
            }
            assertTrue("Size must be greater than zero after adding elements ", rb.size() == array.length);
        }

    }

    @Test
    public void testRedBlackTreeGet() {
        System.out.println("=========Testing get function===========");
        if (selectedType == 1) {
            RedBlackTreeExercise<String> rb = new RedBlackTreeExercise<>();
            String array[] = stringArray;
            for (String bid : array) {
                // add to the red black tree
                rb.add(bid);
            }

            Arrays.sort(array);

            // Add an element
            for (int i = 0; i < array.length; i++) {
                String curr = array[i];
                String getIt = rb.get(i);
                assertEquals(curr, getIt);
            }

            // Test try to get an index which is out of bounds

            String xxx = rb.get(-1);
            assertEquals(xxx, null);
            
        } else if (selectedType == 2) {
            RedBlackTreeExercise<Double> rb = new RedBlackTreeExercise<>();
            Double array[] = doubleArray;
            for (Double bid : array) {
                // add to the red black tree
                rb.add(bid);
            }

            Arrays.sort(array);

            // Add an element
            for (int i = 0; i < array.length; i++) {
                Double curr = array[i];
                Double getIt = rb.get(i);
                assertEquals(curr, getIt);
            }

            // Test try to get an index which is out of bounds

            Double xxx = rb.get(-1);
            assertEquals(xxx, null);
        }
    }

    @Test
    public void testRedBlackTreeRank() {
        System.out.println("=========Testing rank function===========");
        if (selectedType == 1) {
            RedBlackTreeExercise<String> rb = new RedBlackTreeExercise<>();

            String array[] = stringArray;
            for (String item : array) {
                // add to the red black tree
                rb.add(item);
            }
            Arrays.sort(array);

            for (int i = 0; i < array.length; i++) {
                int rank = rb.rank(array[i]);
                assertEquals(i, rank);
            }
            // Try to rank an element that doesn't belong to the array
            int rank = rb.rank("alaowospa");
            assertEquals(rank, -1);
        } else if (selectedType == 2) {
            RedBlackTreeExercise<Double> rb = new RedBlackTreeExercise<>();

            Double array[] = doubleArray;
            for (Double item : array) {
                // add to the red black tree
                rb.add(item);
            }
            Arrays.sort(array);

            for (int i = 0; i < array.length; i++) {
                int rank = rb.rank(array[i]);
                assertEquals(i, rank);
            }
            // Try to rank an element that doesn't belong to the array
            int rank = rb.rank(400.2030);
            assertEquals(rank, -1);
        }

    }

}
