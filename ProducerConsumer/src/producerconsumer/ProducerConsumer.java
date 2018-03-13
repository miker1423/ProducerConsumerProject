/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

/**
 *
 * @author cesarespinosa
 */
public class ProducerConsumer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO Buffer can receive input for size
        // TODO Buffer should be from
        // TODO User can define #producers
        // TODO User can define #consumers
        // TODO Interface should output:
        //  * Buffer size
        //  * Producer # and Time for sleep
        //  * COnsumer # and Time for sleep
        // TODO Button to start
        // TODO Two panels:
        //  * Tasks to do
        //  * Tasks done
        // TODO Both panels should have the status for the buffer
        // TODO Check 0/0
        // TODO Min and Max for the range of the random
        // TODO When task is done, moves from To-Do to Done
        // TODO Verify all inputs
        
        
        Buffer buffer = new Buffer();
         
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        
        producer.start();
        consumer.start();
    }
    
}
