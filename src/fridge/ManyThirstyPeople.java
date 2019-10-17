package fridge;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ManyThirstyPeople {
    private static final int QUEUE_SIZE = 100;
    private static final int N = 100;
    
    /**  Send N thirsty people to the DrinksFridge. */
    public static void main(String[] args) throws IOException {
        // make request and reply queues big enough to hold QUEUE_SIZE messages each
        BlockingQueue<Integer> requests = new ArrayBlockingQueue<>(QUEUE_SIZE);
        BlockingQueue<FridgeResult> replies = new ArrayBlockingQueue<>(QUEUE_SIZE);
        
        DrinksFridge fridge = new DrinksFridge(requests, replies);
        fridge.start();
        
        try {
            // put enough drinks in the fridge to start
            requests.put(-N);

            // send the requests
            for (int x = 1; x <= N; ++x) {
                requests.put(1); // give me 1 drink!
                System.out.println("person #" + x + " looked for a drink");
            }
            // collect the replies
            for (int x = 1; x <= N; ++x) {
                System.out.println("person #" + x + ": " + replies.take());
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("done");
        System.exit(0); // ends the program, including DrinksFridge thread
    }
}
