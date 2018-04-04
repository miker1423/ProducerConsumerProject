package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.sun.org.apache.xerces.internal.parsers.CachingParserPool.SynchronizedGrammarPool;

import javax.swing.*;

public class QueueBuffer {
    Queue<String> Buffer;
    int maxSize;
    JTextArea bufferCurrent;
    DefaultListModel bufferQueue;

    public QueueBuffer(int capacity, JTextArea text, DefaultListModel list) {
        Buffer = new LinkedList<>();
        maxSize = capacity;
        bufferCurrent = text;
        bufferQueue = list;
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
        bufferQueue.removeElement(product);
        if(product == null){
            notify();
            bufferCurrent.setText("Empty");
            return "";
        }

        notify();
        bufferCurrent.setText(product + "...");
        return product;
    }

    synchronized boolean produce(String operation){
        if(Buffer.size() < maxSize){
            this.Buffer.add(operation);
            bufferQueue.addElement(operation);
            notify();
            return true;
        }
        return false;
    }
}