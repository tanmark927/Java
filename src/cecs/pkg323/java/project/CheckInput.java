/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs.pkg323.java.project;
import java.util.Scanner;

/**
 *
 * @author marktan
 */
class CheckInput {
     /**
    * Checks that the inputted value is an integer.
    * @return the valid input.
    */
   public static int checkInt() {
      Scanner in = new Scanner(System.in);
      int input = 0;
      boolean valid = false;
      while(!valid) {
         if(in.hasNextInt()) {
            input = in.nextInt();
            valid = true;
         } else {
            in.next();
            System.out.println("Invalid Input.");
         }
      }
      return input;
   }
   
   /**
    * Takes in a string from the user.
    * @return the inputted String.
    */
   public static String getString() {
      Scanner in = new Scanner(System.in);
      String input = in.nextLine();
      return input;
   }
}
