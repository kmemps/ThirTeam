package main;

import battle.EngageBattle;
import map.TextMap;
import printFormat.BorderedStrings;
import printFormat.OptionsText;
import unit.Player;
import unit.Unit;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

    private static BorderedStrings choices = new OptionsText();

    public static int validUserChoice(int numOfChoices) {
        boolean valid = false;
        int choice = 0;

        do {
            Scanner input = new Scanner(System.in);
            System.out.printf("\n" + "Chose from options 1-%h :", numOfChoices);

            try {
                choice = input.nextInt();
                valid = (choice >=1 && choice<=numOfChoices);
            } catch (InputMismatchException e) {
                System.out.println("INVALID INPUT: You did not enter an integer.");
            }
        } while (!valid);

        return choice;
    }

    public static void locationInput() {
        boolean validLen;

        do {
            Scanner input = new Scanner(System.in);
            System.out.println("\n" + "Enter location (x, y): ");

            String uInput = input.nextLine();
            String[] coordinates = uInput.split(",");
            validLen = (coordinates.length == 2);

            if (validLen) {
                parseCoordinates(coordinates, TextMap.player);
            } else {
                System.out.println("INVALID INPUT: You did not enter the a location in the format: x,y");
            }
        } while (!validLen);
    }

    public static void parseCoordinates(String [] coords, Player player) {
        try {
            int xCoord = Integer.parseInt(coords[0]);
            int yCoord = Integer.parseInt(coords[1]);
            TextMap.updateMap(xCoord, yCoord, player);

        } catch (NumberFormatException n) {
            System.out.println("INVALID INPUT: Please enter values of type integer.");
            locationInput();
        }
    }

    public static String playerAttackInput(String attack, Unit player) {
        String attackType = String.format("1. %s", attack);
        choices.printBox(attackType, "2. Heal", "3. Block");

        int choice = validUserChoice(3);

        if (choice == 1) {
            return attack.toUpperCase();
        } else if (choice == 2 && !EngageBattle.playerHealCount(player)) {
            return null;
        } else if (choice == 2) {
            return "HEAL";
        } else if (choice == 3) {
            return "DEFEND";
        }
        return null;
    }

    public static void itemPickupInput() {

    }
}
