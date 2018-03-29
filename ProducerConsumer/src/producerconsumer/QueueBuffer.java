package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.sun.org.apache.xerces.internal.parsers.CachingParserPool.SynchronizedGrammarPool;

public class QueueBuffer {
    Queue<String> Buffer;
    int maxSize;

    public QueueBuffer(int capacity) {
        Buffer = new LinkedList<>();
        maxSize = capacity;
    }

    synchronized String consume(){
        String product = "";
        if(product.equals("")){
            try {
                wait(100);
            } catch (Exception e) {
                Logger.getLogger(QueueBuffer.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        product = this.Buffer.poll();
        if(product == null){
            notify();
            return "";
        }

        notify();
        return product;
    }

    synchronized void produce(String operation){
        this.Buffer.add(operation);

        notify();
    }
}