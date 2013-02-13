/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiltzu.ui.component.food;

import java.awt.Graphics;
import java.awt.Rectangle;
import org.wiltzu.ui.component.Drawable;

/**
 *
 * @author Ville
 */
class BasicFood implements Drawable, Food {
    
    private final int x, y, size = 5;
    private boolean eaten;
    
    public BasicFood(int x, int y) {
        this.x = x;
        this.y = y;
        this.eaten = false;
    }

    @Override
    public void draw(Graphics g) {
        if(!eaten) {
          g.drawRect(x, y, size, size);   
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    @Override
    public void eat() {
        eaten = true;
    }

    @Override
    public boolean isEaten() {
        return eaten;
    }
 
}
