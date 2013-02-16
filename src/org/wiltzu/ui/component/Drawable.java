package org.wiltzu.ui.component;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *<p>Interface for classes, that needs to draw something on screen.</p>
 *
 * @author Ville Ahti
 */
public interface Drawable {

    /**
     * <p>For Drawing to specific graphic context</p>
     * 
     * @param g Graphics context where to draw
     */
    void draw(Graphics g);

    /**
     * <p>Getter for bounds of the Drawable class.</p>
     * 
     * @return Bounds of the Drawable class
     */
    Rectangle getBounds();
    
}
