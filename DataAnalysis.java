
/**
 * Load data first using the CSVReader class
 * Add it to ArrayLists<> all the columns
 * Now use the red black tree data structure to analyse the data
 * 
 */
import java.io.*;
import java.util.*;

public class DataAnalysis {
    public static void main(String[] args) {
        RedBlackTreeExercise<String> dateTime = new RedBlackTreeExercise<>();
        RedBlackTreeExercise<String> timeStamps = new RedBlackTreeExercise<>();
        RedBlackTreeExercise<Double> A_bids = new RedBlackTreeExercise<>();
        RedBlackTreeExercise<Double> A_offers = new RedBlackTreeExercise<>();
        RedBlackTreeExercise<Double> B_bids = new RedBlackTreeExercise<>();
        RedBlackTreeExercise<Double> B_offers = new RedBlackTreeExercise<>();

        // Fire!!
        int count = 1;
        String path = "testData.csv";
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            String row = "";
            while ((row = csvReader.readLine()) != null) {
                if (count == 1) {
                    count++; // Skip the headings
                    continue;
                }
                String[] data = row.split(",");
                dateTime.add(data[0]);
                String timeStamp = data[1];
                timeStamps.add(timeStamp);
                Double A_bid = Double.parseDouble(data[2]);
                Double A_offer = Double.parseDouble(data[3]);
                Double B_bid = Double.parseDouble(data[4]);
                Double B_offer = Double.parseDouble(data[5]);
                A_bids.add(A_bid);
                A_offers.add(A_offer);
                B_bids.add(B_bid);
                B_offers.add(B_offer);
            }
            /*System.out.println("=============================Displaying dates================================");
            dateTime.prettyPrint();
            System.out.println("=============================Displaying timestamps===========================");
            timeStamps.prettyPrint();
            System.out.println("=============================Displaying A_bids================================");
            A_bids.prettyPrint();
            System.out.println("===============================Displaying A_offers=============================");
            A_offers.prettyPrint();
            System.out.println("==============================Displaying B_bids=================================");
            B_bids.prettyPrint();
            System.out.println("==============================Displaying B_offers=================================");
            B_offers.prettyPrint(); */
            
            // Analyse the data
            Double biggestA_bid = A_bids.get(A_bids.size() - 1);
            Double smallestA_bid = A_bids.get(0);
            Double medianA_bid = A_bids.get(A_bids.size() / 2);
            Double biggestA_offer = A_offers.get(A_offers.size()-1);
            Double smallestA_offer = A_offers.get(0);
            Double medianA_offer = A_offers.get(A_offers.size() / 2);

            System.out.printf(
                    "\nBiggest A bid: %.6f\n" + "Smallest A bid: %.6f\n" + "Median A bid: %.6f\n"
                            + "Biggest A offer: %.6f\n" + "Smallest A offer: %.6f\n" + "Median A offer: %.6f\n",
                    biggestA_bid, smallestA_bid, medianA_bid, biggestA_offer, smallestA_offer, medianA_offer);
            
            //Get the rank of this bid in A_bids
            int rank = A_bids.rank(1.1269261);
            System.out.printf("\n%.7f is the (%dth) in the list of A_bids\n",1.1269261, rank);
            // B_bids

            Double biggestB_bid = B_bids.get(B_bids.size() - 1);
            Double smallestB_bid = B_bids.get(0);
            Double medianB_bid = B_bids.get(B_bids.size() / 2);
            Double biggestB_offer = B_offers.get(B_offers.size() - 1);
            Double smallestB_offer = B_offers.get(0);
            Double medianB_offer = B_offers.get(B_offers.size() / 2);
            System.out.printf(
                    "\nBiggest B bid: %.6f\n" + "Smallest B bid: %.6f\n" + "Median B bid: %.6f\n"
                            + "Biggest B offer: %.6f\n" + "Smallest B offer: %.6f\n" + "Median B offer: %.6f\n",
                    biggestB_bid, smallestB_bid, medianB_bid, biggestB_offer, smallestB_offer, medianB_offer);
            //Get the rank of this bid in A_bids
            rank = B_bids.rank(1.12692026666667);
            System.out.printf("\n%.7f is the (%dth) in the list of B_bids\n",1.12692026666667, rank);
            
        } catch (IOException ie) {
            System.out.println("Could not load file: " + path);
            ie.printStackTrace();
        }

    }
}
