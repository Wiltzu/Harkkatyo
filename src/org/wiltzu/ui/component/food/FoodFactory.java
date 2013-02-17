package org.wiltzu.ui.component.food;

import java.util.Random;

/**
 * <p>Factory class that generates food in randomized positions.</p>
 * 
 * @author Ville Ahti
 */
public class FoodFactory {
    
    private static int x, y;
    private static Random randomizer = new Random();
    
    /**
     * <p>Factory method returns new food in randomized position.</p>
     * 
     * @return food in randomized position
     */
    public static Food getFood() {
        randomizePosition();
        return new BasicFood(x, y);
    }
    
    /**
     * <p>randomizes x and y coordinates.</p>
     */
    private static void randomizePosition() {
        x = randomizer.nextInt(410) + 3;
        y = randomizer.nextInt(400) + 3;
    } 
}
