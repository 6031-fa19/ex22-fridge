package fridge;

import java.util.concurrent.BlockingQueue;

/**
 * A mutable type representing a refrigerator containing drinks, using
 * message passing to communicate with clients.
 */
public class DrinksFridge {
    
    private int drinksInFridge;
    private final BlockingQueue<Integer> in;
    private final BlockingQueue<FridgeResult> out;
    
    // Abstraction function:
    //   AF(drinksInFridge, in, out) = a refrigerator containing `drinksInFridge` drinks
    //                                 that takes requests from `in` and sends replies to `out`
    // Rep invariant:
    //   drinksInFridge >= 0

    private void checkRep() {
        assert drinksInFridge >= 0;
        assert in != null;
        assert out != null;
    }
    
    /**
     * Make a DrinksFridge that will listen for requests and generate replies.
     * 
     * @param requests queue to receive requests from
     * @param replies queue to send replies to
     */
    public DrinksFridge(BlockingQueue<Integer> requests, BlockingQueue<FridgeResult> replies) {
        this.drinksInFridge = 0;
        this.in = requests;
        this.out = replies;
        checkRep();
    }
    
    /**
     * Start handling drink requests.
     */
    public void start() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    // TODO: we may want a way to stop the thread
                    try {
                        // block until a request arrives
                        int n = in.take();
                        FridgeResult result = handleDrinkRequest(n);
                        out.put(result);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    /**
     * Take (or add) drinks from the fridge.
     * @param n if >= 0, removes up to n drinks from the fridge, until fridge is empty;
     *          if < 0, adds -n drinks to the fridge.
     * @return DrinkResult reporting how many drinks were actually added or removed
     *      and how many drinks are left in the fridge. 
     */
    private FridgeResult handleDrinkRequest(int n) {
        // adjust request to reflect actual drinks available
        int change = Math.min(n, drinksInFridge);
        drinksInFridge -= change;
        checkRep();
        return new FridgeResult(change, drinksInFridge);
    }
}
