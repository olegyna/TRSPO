/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lab-2
 */
public class potok2 extends Thread {
  String name;
    potok2(String name){
    this.name=name;
    }
    @Override
    public void run () {
while (true) {
        System.out.println("Я работаю! "+name);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(potok2.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}
    
}
