package org.wiltzu.ui.component.food;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.wiltzu.ui.component.Drawable;

/**
 *<p>Simple implementation of the Food interface.</p>
 *
 * @author Ville Ahti
 */
public class BasicFood implements Drawable, Food {
    
    private final int x, y, size = 5;
    private boolean eaten;
    
    /**
     * <p>Constructor for this class.</p>
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public BasicFood(int x, int y) {
        this.x = x;
        this.y = y;
        this.eaten = false;
    }

    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Drawable#draw(java.awt.Graphics)
     */
    @Override
    public void draw(Graphics g) {
        if(!eaten) {
          g.drawRect(x, y, size, size);   
        }
    }

    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Drawable#getBounds()
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.food.Food#eat()
     */
    @Override
    public void eat() {
        eaten = true;
    }

    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.food.Food#isEaten()
     */
    @Override
    public boolean isEaten() {
        return eaten;
    }
 
}
