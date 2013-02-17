package org.wiltzu.ui.component.food;

/**
 *<p>Interface that represents food in the worm game.</p>
 *
 * @author Ville Ahti
 */
public interface Food {
    
	/**
     * <p>informs that food is eaten</p>
     */
    void eat();
    
    /**
     * <p>Getter for an information about whether the food is eaten or not.</p>
     * 
     * @return info whether food is eaten or not
     */
    boolean isEaten();
}
