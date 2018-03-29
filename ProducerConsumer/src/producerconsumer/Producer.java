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
public class Producer extends Thread{
    
    Buffer buffer;
    long WaitTime;

    public Producer(Buffer buffer, long waitTime) {
        this.buffer = buffer;
        WaitTime = waitTime;
    }
    
  
    @Override
    public void run(){
        
        System.out.println("Running Producer...");
        String products = "AEIOU";
        Random r = new Random(System.currentTimeMillis());
        char product = 0;
        
        while (true) {            
            product = products.charAt(r.nextInt(5));
            this.buffer.produce(product);
            System.out.println("Producer produced: " + product);
            
            try {
                Thread.sleep(WaitTime);
            } catch (Exception e) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
