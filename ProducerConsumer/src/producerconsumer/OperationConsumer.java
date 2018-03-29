package producerconsumer;

public class OperationConsumer extends Thread {

    private QueueBuffer Buffer;
    private OperationParser parser = new OperationParser();
    private long TimeToWait;

    public OperationConsumer(QueueBuffer buffer, int timeToWait) {
        Buffer = buffer;
        TimeToWait = timeToWait;
    }

    @Override
    public void run() {
        
        System.out.println("Running Consumer...");
        String product = 0;
        
        while (true) {
            product = this.Buffer.consume();
            if(product.equals("")){
                continue;
            }
            
            System.out.println("Consumer consumed: " + product);
            System.out.println("Result: " + parser.evaluate(product));
            
            try {
                Thread.sleep(TimeToWait);
            } catch (Exception e) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}