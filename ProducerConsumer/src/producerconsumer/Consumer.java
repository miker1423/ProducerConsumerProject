/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author cesarespinosa
 */
public class Consumer extends Thread{
    
    Buffer buffer;
    long WaitTime;

    public Consumer(Buffer buffer, long waitTime) {
        this.buffer = buffer;
        WaitTime = waitTime;
    }
    
  
    @Override
    public void run(){
        
        System.out.println("Running Consumer...");
        char product = 0;
        
        while (true) {
            product = this.buffer.consume();
            System.out.println("Consumer consumed: " + product);
            
            try {
                Thread.sleep(WaitTime);
            } catch (Exception e) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
