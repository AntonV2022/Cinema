package org.example;

import java.util.Scanner;

public class Cinema {

    private static int COUNT_OF_ROWS;
    private static int COUNT_OF_SEATS;
    private static String[][] CINEMA;
    private static int COUNT_OF_TICKETS = 0;
    private static int COUNT_OF_MONEY = 0;


    static void createCinema() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        COUNT_OF_ROWS = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        COUNT_OF_SEATS = scanner.nextInt();

        CINEMA = new String[COUNT_OF_ROWS + 1][COUNT_OF_SEATS + 1];
        System.out.println();

        for (int i = 0; i < CINEMA.length; i++) {
            if (i == 0) {
                for (int j = 0; j < CINEMA[0].length; j++) {
                    if (j == 0) {
                        CINEMA[i][j] = " ";
                    } else {
                        CINEMA[i][j] = String.valueOf(j);
                    }
                }
            } else {
                for (int j = 0; j <= CINEMA[0].length - 1; j++) {
                    if (j == 0) {
                        CINEMA[i][j] = String.valueOf(i);
                    } else {
                        CINEMA[i][j] = "S";
                    }
                }
            }
        }
    }

    static void showTheSeats(String[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        for (String[] strings : cinema) {
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean checkCinema(int rows, int seats) {
        return (rows - 1) * (seats - 1) <= 60;
    }

    static boolean checkPlace(String[][] cinema, int inputRow, int inputSeat) {
        return !cinema[inputRow][inputSeat].equals("B");
    }

    static void buyTicket(String[][] cinema) {
        Scanner scanner = new Scanner(System.in);
        boolean correctInput;
        do {
            System.out.println("Enter a row number:");
            int inputRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int inputSeat = scanner.nextInt();

            if (inputRow >= COUNT_OF_ROWS || inputSeat >= COUNT_OF_SEATS) {
                System.out.println("Wrong input!");
                correctInput = true;
            } else {
                if (!checkPlace(cinema, inputRow, inputSeat)) {
                    System.out.println("That ticket has already been purchased!");
                    System.out.println();
                    correctInput = true;
                } else {
                    if (checkCinema(COUNT_OF_ROWS, COUNT_OF_SEATS)) {
                        System.out.println("Ticket price: $" + 10);
                        cinema[inputRow][inputSeat] = "B";
                        COUNT_OF_TICKETS++;
                        COUNT_OF_MONEY += 10;
                    } else if (inputRow < COUNT_OF_ROWS / 2) {
                        System.out.println("Ticket price: $" + 10);
                        cinema[inputRow][inputSeat] = "B";
                        COUNT_OF_TICKETS++;
                        COUNT_OF_MONEY += 10;
                    } else if (inputRow >= COUNT_OF_ROWS / 2) {
                        System.out.println("Ticket price: $" + 8);
                        cinema[inputRow][inputSeat] = "B";
                        COUNT_OF_TICKETS++;
                        COUNT_OF_MONEY += 8;
                    }
                    correctInput = false;
                }
            }
        } while (correctInput);
    }

    static void statistics(String[][] cinema) {
        System.out.println("Number of purchased tickets: " + COUNT_OF_TICKETS);
        float money = COUNT_OF_TICKETS * 10000000 / ((COUNT_OF_ROWS - 1) * (COUNT_OF_SEATS - 1));
        money = money / 100000;
        int moneySmall = (COUNT_OF_ROWS - 1) * (COUNT_OF_SEATS - 1) * 10;
        int moneyBig = (((COUNT_OF_ROWS - 1) / 2) * 10 + (COUNT_OF_ROWS - 1 - (COUNT_OF_ROWS - 1) / 2) * 8) * (COUNT_OF_SEATS - 1);
        System.out.printf("Percentage: %.2f%%", money);
        System.out.println();
        System.out.println("Current income: $" + COUNT_OF_MONEY);
        if (checkCinema(COUNT_OF_ROWS, COUNT_OF_SEATS)) {
            System.out.println("Total income: $" + moneySmall);
        } else {
            System.out.println("Total income: $" + moneyBig);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        createCinema();
        int x = 100;
        while (x != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            x = scanner.nextInt();
            switch (x) {
                case 1:
                    showTheSeats(CINEMA);
                    break;
                case 2:
                    buyTicket(CINEMA);
                    break;
                case 3:
                    statistics(CINEMA);
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
            }
        }
    }
}