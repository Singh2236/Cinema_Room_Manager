package Java;

import java.util.Scanner;

public class Cinema {
    static int  noOfPurchasedTickets = 0;
    static int income = 0;
    public static void main(String[] args) {
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of rows:");
            int rows = 1 + scanner.nextInt();
            System.out.println("Enter the number of seats in each row:");
            int columns = 1 + scanner.nextInt();
            System.out.println();

            int totalSeats = (rows - 1) * (columns - 1);
            char[][] seats = new char[rows][columns];
            createInitialSeatArrangements(seats);


            //infinite loop except exited
            while (true) {
                System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
                int userInput = scanner.nextInt();

                // Show the seats
                if (userInput == 1) {
                    printSeatArrangements(seats);
                }

                //Buy a ticket
                // Update: Check if the ticket has not already been purchased.
                else if (userInput == 2) {
                    buyATicket(seats, rows, columns);

                }
                //Statistics
                else if (userInput == 3) {
                    //Number of purchased seats
                    System.out.println("Number of purchased tickets:" + noOfPurchasedTickets);

                    //The number of purchased tickets represented as a percentage.
                    // Percentages should be rounded to 2 decimal places;
                    float percentage  = ((float) noOfPurchasedTickets / totalSeats);
                    percentage *= 100;
                    System.out.printf("Percentage: %.2f%%" , percentage );
                    System.out.println();

                    //Current Income
                    System.out.println("Current income: $" + income);

                    //Total Income that shows, how much money the theater will get if all the tickets are sold.
                    int frontSeats = (((rows -1) /2 ) * (columns -1));
                    int totalIncome = (frontSeats * 10) + (totalSeats - frontSeats) * 8;
                    System.out.println("Total income: $" + totalIncome);


                }
                // Exit
                else if (userInput == 0) {
                    break;
                }
                //Invalid input
                else {
                    System.out.println("INVALID INPUT");
                }
            }
        }
    }

    public static void createInitialSeatArrangements(char[][] seats) {
        int i = 49, j = 49;
        //initial seat Arrangement
        for (int nRows = 0; nRows < seats.length; nRows++) {
            for (int nColumns = 0; nColumns < seats[nRows].length; nColumns++) {
                if (nRows == 0 && nColumns == 0) {
                    seats[0][0] = ' ';
                }
                else if (nRows == 0) {
                    seats[0][nColumns] = (char) i;
                    i++;
                }
                else if (nColumns == 0) {
                    seats[nRows][0] = (char) j;
                    j++;
                }
                else
                    seats[nRows][nColumns] = 'S';
            }
        }

    }

    public static void printSeatArrangements(char[][] seats) {
        System.out.println("Cinema:");
        for (char[] rows : seats) {
            for (char columns : rows) {
                System.out.print(columns + " ");
            }
            System.out.println();
        }
    }

    public static int price(int rows, int columns, int setRow) {
        int ticketPrice;
        if ((rows-1) * columns -1 <= 60) {
            ticketPrice = 10;
        }
        else {
            if (setRow > (rows-1) / 2) {
                ticketPrice = 8;
            }
            else {
                ticketPrice = 10;
            }
        }
        return ticketPrice;
    }

    public static void updateSeatArrangement(char[][] seats, int setRow, int setColumn, int rows, int columns) {
        //check for out of bonds error
        if (setRow <= 0 || setRow > rows - 1 || setColumn <= 0 || setColumn > columns - 1) {
            System.out.println("Wrong Input!");
            buyATicket(seats, rows, columns);
        }//check if the seat has been already occupied
        else if (seats[setRow][setColumn] == 'B') {
            System.out.println("That ticket has already been purchased. Please enter different seat coordinates!");
            buyATicket(seats, rows, columns);
        }
        //else
        else {
            seats[setRow][setColumn] = 'B';
            //Update No. of purchased Tickets
            noOfPurchasedTickets += 1;
            System.out.println("Ticket price: $" + price(rows, columns, setRow));
            income += price(rows, columns, setRow);
        }
    }

    public static void buyATicket(char[][] seats, int rows, int columns) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int setRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int setColumn = scanner.nextInt();

        updateSeatArrangement(seats, setRow, setColumn, rows, columns);

    }
}