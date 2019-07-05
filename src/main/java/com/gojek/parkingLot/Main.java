package com.gojek.parkingLot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = null;
        Commands commandObj  = new Commands();;
        if (args.length >0){
            //read file

            System.out.println(String.format("Reading from file %s", args[0]));
            File file = new File(args[0]);
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{

            scanner = new Scanner(System.in);

        }

        String inputStr= scanner.nextLine();
        while(!inputStr.equalsIgnoreCase("exit") ){


            commandObj.interpret(inputStr);
            inputStr= scanner.nextLine();
        }

        scanner.close();

    }
}
