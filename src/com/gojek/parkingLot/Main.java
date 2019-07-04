package com.gojek.parkingLot;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        if (args.length >1){
            //read file

            System.out.println(String.format("Reading from file %s %s", args[0], args[1]));
        }
        else{
            // create a scanner so we can read the command-line input
            Commands commandObj = new Commands();
            Scanner scanner = new Scanner(System.in);
            String inputStr= scanner.nextLine();
            while(!inputStr.equalsIgnoreCase("exit") ){
                /*
                //  prompt for the user's name
                System.out.print("Enter your name: ");

                // get their input as a String
                String username = scanner.next();

                // prompt for their age
                System.out.print("Enter your age: ");

                // get the age as an int
                int age = scanner.nextInt();

                System.out.println(String.format("%s, your age is %d", username, age));
                */

                commandObj.interpret(inputStr);
                inputStr= scanner.nextLine();
            }

        }


    }
}
