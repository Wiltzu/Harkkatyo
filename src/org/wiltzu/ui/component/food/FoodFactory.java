/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiltzu.ui.component.food;

import java.util.Random;

/**
 *
 * @author Ville
 */
public class FoodFactory {
    
    private static int x, y;
    private static Random randomizer = new Random();
    
    public static Food getFood() {
        randomizePosition();
        return new BasicFood(x, y);
    }
    
    private static void randomizePosition() {
        x = randomizer.nextInt(410) + 3;
        y = randomizer.nextInt(400) + 3;
    } 
}
