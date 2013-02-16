
package org.wiltzu.ui.component;

import java.awt.Rectangle;

import javax.swing.JPanel;

import org.wiltzu.ui.component.exception.CollisionException;
import org.wiltzu.util.Direction;

/**
 *
 * @author Ville
 */
public interface Controlable extends Drawable{
	
	void setX(int x);

    void setY(int y);
    
    Rectangle getLastBounds();

    Direction getDirection();
    
    Direction getLastDirection();

    void setDirection(Direction dir);
    
    void update(JPanel container) throws CollisionException;
    
}
