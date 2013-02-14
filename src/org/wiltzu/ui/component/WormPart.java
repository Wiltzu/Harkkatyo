/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiltzu.ui.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import org.wiltzu.ui.component.exception.CollisionException;
import org.wiltzu.util.Direction;

/**
 *
 * @author Ville
 */
public class WormPart implements Controlable {

    private int x, y, size, last_x, last_y;
    private Direction direction, lastDirection;
    private Rectangle bounds, lastBounds;

    public WormPart(int x_pos, int y_pos, int size, Direction direction) {
        this.x = x_pos;
        this.y = y_pos;
        this.size = size;
        this.bounds = new Rectangle(x_pos, y_pos, size, size);
        this.lastBounds = new Rectangle(size, size);
        this.direction = direction;
        //this.lastDirection = this.getDirection();
    }

    @Override
    public void setX(int x) {
        this.x = x;
        bounds.setLocation(x, y);
    }

    @Override
    public void setY(int y) {
        this.y = y;
        bounds.setLocation(x,y);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
    
   
    @Override
    public Rectangle getLastBounds() {
        return lastBounds;
    }
    
    @Override
    public Direction getDirection() {
        return direction;
    }
    
    @Override
    public void update(JPanel container) throws CollisionException {
        Rectangle containerBounds = container.getBounds();
        
        lastBounds.setLocation(x, y); //old position
        if(getDirection() == Direction.DOWN)
            y+=size;
        else if(getDirection() == Direction.UP) 
            y-=size;
        else if(getDirection() == Direction.LEFT) 
            x-=size;
        else if(getDirection() == Direction.RIGHT)
            x+=size;
        bounds.setLocation(x, y); //new position
        
        boolean contains = containerBounds.contains(this.getBounds());
        if(!contains) {
            throw new CollisionException();  
        }
        
    }
    
    @Override
    public void draw(Graphics g) {
       g.drawOval(x, y, size, size);
       g.fillOval(x, y, size, size);
    }
    
    @Override
    public void setDirection(Direction dir) {
        lastDirection = getDirection(); //setting last Direction in memory
        if(Direction.getOpposite(dir) != lastDirection) {
            switch(dir) {
                case UP:
                    direction = Direction.UP;
                    break;
                case DOWN:
                    direction = Direction.DOWN;
                    break;
                case LEFT:
                    direction = Direction.LEFT;
                    break;
                case RIGHT:
                    direction = Direction.RIGHT;
                    break;
            }                
        }
    }

    @Override
    public Direction getLastDirection() {
        return lastDirection;
    }
    
    
    
    
    
}
