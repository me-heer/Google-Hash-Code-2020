
import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
            //File file = new File("a_example.txt");
            //Scanner in = new Scanner(file);
            int numberOfBooks = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
            int numberOfLibraries = in.nextInt();
            int numberOfDays = in.nextInt();
            int[] books = new int[numberOfBooks];
            for (int i = 0; i < numberOfBooks; i++)
                books[i] = in.nextInt();

            int[] signedUpLibraries = new int[numberOfLibraries];
            int[][] booksSentByEachLibrary = new int[numberOfLibraries][];
            int[][] booksInEachLibrary = new int[numberOfLibraries][];
            int[] signupDaysInEachLibrary = new int[numberOfLibraries];
            int[] canShipBooksInLibrary = new int[numberOfLibraries];
            int[] totalBooksInEachLibrary = new int[numberOfLibraries];
            int[] totalBooksSentByEachLibrary = new int[numberOfLibraries];
            Arrays.fill(totalBooksInEachLibrary, 0);

            for (int i = 0; i < numberOfLibraries; i++) {
                int currentLibraryBooks = in.nextInt(); // 5
                booksInEachLibrary[i] = new int[currentLibraryBooks];
                booksSentByEachLibrary[i] = new int[currentLibraryBooks];
                signupDaysInEachLibrary[i] = in.nextInt(); // 2
                canShipBooksInLibrary[i] = in.nextInt(); // 2
                for (int j = 0; j < currentLibraryBooks; j++) {
                    booksInEachLibrary[i][j] = in.nextInt();
                    totalBooksInEachLibrary[i]++;
                }
            }

            boolean isSigningUp = false, isReadyToShip = false;
            int signupDaysPassed = 0, numberOfSignupDaysNeeded = 0, totalSignedUpLibraries = 0;
            int signingUpLibrary = 0, signedUp = 0, shipping = 0;
            int[] currentLibraries = new int[numberOfLibraries];
            int[] previousSentBooks = new int[numberOfLibraries];
            Arrays.fill(previousSentBooks, 0);
            for (int i = 0; i < numberOfDays; i++) {
                //System.out.print("Day " + i + " ");
                if (isSigningUp == false && totalSignedUpLibraries != numberOfLibraries) {
                    // startSigningUp
                    //System.out.println("Starting sign up of library:" + signingUpLibrary);
                    isSigningUp = true;
                    numberOfSignupDaysNeeded = signupDaysInEachLibrary[signingUpLibrary];
                }

                if(totalSignedUpLibraries > 0){
                    for(int j = 0; j < totalSignedUpLibraries; j++)
                    {
                        int current = signedUpLibraries[j]; 
                        if(previousSentBooks[current] < booksInEachLibrary[current].length)
                        for(int k = 0; k < canShipBooksInLibrary[current]; k++)
                        {
                            if(previousSentBooks[current] < booksInEachLibrary[current].length)
                            {
                                //System.out.print(booksInEachLibrary[current][   previousSentBooks[current]++  ]);
                                previousSentBooks[current]++;
                                totalBooksSentByEachLibrary[current]++;
                            }
                                
                        }
                    }

                }

                if (signupDaysPassed + 1 == numberOfSignupDaysNeeded) {
                    //System.out.println("SignUp Complete.");
                    signedUpLibraries[signedUp++] = signingUpLibrary;
                    totalSignedUpLibraries++;
                    isSigningUp = false;
                    isReadyToShip = true;
                    signingUpLibrary++;
                    signupDaysPassed = 0;
                }
                if(isSigningUp)
                    signupDaysPassed++;
                //System.out.println();
            }
            
            System.out.println(totalSignedUpLibraries);
            for(int i = 0; i < totalSignedUpLibraries; i++)
            {
                System.out.print(i + " " + totalBooksSentByEachLibrary[i] + "\n");
                for(int j = 0; j < totalBooksSentByEachLibrary[i]; j++)
                {
                    System.out.print(booksInEachLibrary[i][j] + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}