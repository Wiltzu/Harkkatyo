package org.wiltzu.ui.component;

import java.awt.Rectangle;

import javax.swing.JPanel;

import org.wiltzu.ui.component.exception.CollisionException;
import org.wiltzu.util.Direction;

/**
 *<p>Interface for Drawable classes that can be controlled and their collisions can be checked.</p>
 *
 * @author Ville Ahti
 */
public interface Controlable extends Drawable{
	
	/**
	 * <p>Setter for x coordinate.</p>
	 * 
	 * @param x coordinate
	 */
	void setX(int x);

    /**
     * <p>Setter for y coordinate.</p>
     * 
     * @param y cooredinate
     */
    void setY(int y);
    
    /**
     * <p>Getter for previous bounds.</p>
     * 
     * @return previous bounds
     */
    Rectangle getLastBounds();

    /**
     * <p>Getter for current bounds.</p>
     * 
     * @return current bounds
     */
    Direction getDirection();
    
    /**
     * <p>Getter for previous direction</p>
     * 
     * @return previous direction
     */
    Direction getLastDirection();

    /**
     * <p>Setter for new direction.</p>
     * 
     * @param dir to set
     */
    void setDirection(Direction dir);
    
    /**
     * <p>for updating status of the class and checking collisions</p>
     * 
     * @param container
     * @throws CollisionException
     */
    void update(JPanel container) throws CollisionException;
    
}
