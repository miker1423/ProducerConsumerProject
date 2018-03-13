/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesarespinosa
 */
public class Consumer extends Thread{
    
    Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    
  
    @Override
    public void run(){
        
        System.out.println("Running Consumer...");
        char product = 0;
        
        while (true) {
            product = this.buffer.consume();
            System.out.println("Consumer consumed: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
