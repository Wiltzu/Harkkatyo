package org.wiltzu.ui.component;

import org.wiltzu.ui.component.food.Food;

/**
 * <p>Interface that represents classes that eat Food.</p>
 *
 * @author Ville Ahti
 */
public interface Eater {

    /**
     * <p>for feeding Eater.</p>
     * 
     * @param food to eat
     */
    void feed(Food food);
    
}
