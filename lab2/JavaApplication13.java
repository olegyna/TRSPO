
package javaapplication13;

import java.util.Scanner;

public class JavaApplication13  {


    public static void main(String[] args) {

     Scanner scan = new Scanner(System.in);
      
      while (true)
      {
          String  name  = scan.nextLine();
          potok2 p = new potok2(name);
          p.start();
      }
      
     
      }
    }
    

   


