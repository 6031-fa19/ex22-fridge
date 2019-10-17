package fridge;

/**
 * An immutable message describing the result of taking or adding drinks to a DrinksFridge.
 */
public class FridgeResult {
    private final int drinksTakenOrAdded;
    private final int drinksLeftInFridge;
    // Rep invariant: 
    //   TODO
    
    /**
     * Make a new result message.
     * @param drinksTakenOrAdded
     * @param drinksLeftInFridge
     */
    public FridgeResult(int drinksTakenOrAdded, int drinksLeftInFridge) {
        this.drinksTakenOrAdded = drinksTakenOrAdded;
        this.drinksLeftInFridge = drinksLeftInFridge;
    }
    
    // TODO: we will want more observers, but for now...
    
    @Override public String toString() {
        return (drinksTakenOrAdded >= 0 ? "you took " : "you put in ") 
                + Math.abs(drinksTakenOrAdded) + " drinks, fridge has " 
                + drinksLeftInFridge + " left";
    }
}
