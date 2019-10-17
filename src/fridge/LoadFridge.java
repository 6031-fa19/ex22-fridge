package fridge;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LoadFridge {
    
    /** Create and use a drinks fridge. */
    public static void main(String[] args) {
        
        BlockingQueue<Integer> requests = new LinkedBlockingQueue<>();
        BlockingQueue<FridgeResult> replies = new LinkedBlockingQueue<>();
        
        // start an empty fridge
        DrinksFridge fridge = new DrinksFridge(requests, replies);
        fridge.start();
        
        try {
            // deliver some drinks to the fridge
            requests.put(-42);
            
            // maybe do something concurrently...
            
            // read the reply
            System.out.println(replies.take());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("done");
        System.exit(0); // ends the program, including DrinksFridge
    }
}
