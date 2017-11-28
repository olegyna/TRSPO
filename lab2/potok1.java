/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lab-2
 */
public class potok1 extends Thread {
int a,b;
  Scanner scan = new Scanner(System.in);
    @Override
    public void run () {
 while(true){
      System.out.println("Введите первое число");
      a = scan.nextInt();
      System.out.println("Введите второе число");
      b = scan.nextInt();
      System.out.println("Сумма равна "+(a+b));
}
}
}

