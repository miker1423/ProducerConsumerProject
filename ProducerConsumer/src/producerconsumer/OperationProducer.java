package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

public class OperationProducer extends Thread {

    QueueBuffer Buffer;
    OperationBuilder Builder;
    long TimeToWait;

    public OperationProducer(QueueBuffer buffer, int timeToWait, OperationBuilder builder) {
        Buffer = buffer;
        TimeToWait = timeToWait;
        Builder = builder;
    }


    @Override
    public void run() {
        String product;

        while (true) {            
            product = Builder.generate();
            this.Buffer.produce(product);
            System.out.println("Producer produced: " + product);
            
            try {
                Thread.sleep(WaitTime);
            } catch (Exception e) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
    }
}